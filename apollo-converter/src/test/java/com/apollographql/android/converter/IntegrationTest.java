package com.apollographql.android.converter.pojo;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.io.Files;

import com.apollographql.android.api.graphql.Error;
import com.apollographql.android.api.graphql.Response;
import com.apollographql.android.converter.ApolloConverterFactory;
import com.apollographql.android.converter.CustomTypeAdapter;
import com.apollographql.android.converter.pojo.type.CustomType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.google.common.truth.Truth.assertThat;

public class IntegrationTest {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

  private Service service;

  interface Service {
    @POST("graphql")
    Call<Response<AllPlanets.Data>> heroDetails(@Body AllPlanets query);

    @POST("graphql")
    Call<Response<ProductsWithDate.Data>> productsWithDate(@Body ProductsWithDate query);
  }

  @Rule public final MockWebServer server = new MockWebServer();

  @Before public void setUp() {
    CustomTypeAdapter<Date> dateCustomTypeAdapter = new CustomTypeAdapter<Date>() {
      @Override public Date decode(String value) {
        try {
          return DATE_FORMAT.parse(value);
        } catch (ParseException e) {
          throw new RuntimeException(e);
        }
      }

      @Override public String encode(Date value) {
        return DATE_FORMAT.format(value);
      }
    };

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(server.url("/"))
        .addConverterFactory(new ApolloConverterFactory.Builder()
            .withResponseFieldMapper(AllPlanets.Data.class, new AllPlanets.Data.Mapper(AllPlanets.Data.FACTORY))
            .withResponseFieldMapper(ProductsWithDate.Data.class, new ProductsWithDate.Data.Mapper(ProductsWithDate
                .Data.FACTORY))
            .withCustomTypeAdapter(CustomType.DATETIME, dateCustomTypeAdapter)
            .build())
        .build();
    service = retrofit.create(Service.class);
  }

  @SuppressWarnings("ConstantConditions") @Test public void allPlanetQuery() throws Exception {
    server.enqueue(mockResponse("src/test/graphql/allPlanetsResponse.json"));

    Call<Response<AllPlanets.Data>> call = service.heroDetails(new AllPlanets());
    Response<AllPlanets.Data> body = call.execute().body();
    assertThat(body.isSuccessful()).isTrue();

    assertThat(server.takeRequest().getBody().readString(Charsets.UTF_8))
        .isEqualTo("{\"query\":\"query TestQuery {  "
            + "allPlanets(first: 300) {"
            + "    planets {"
            + "      ...PlanetFragment"
            + "      filmConnection {"
            + "        totalCount"
            + "        films {"
            + "          title"
            + "          ...FilmFragment"
            + "        }"
            + "      }"
            + "    }"
            + "  }"
            + "}"
            + "fragment PlanetFragment on Planet {"
            + "  name"
            + "  climates"
            + "  surfaceWater"
            + "}"
            + "fragment FilmFragment on Film {"
            + "  title"
            + "  producers"
            + "}\",\"variables\":{}}");

    AllPlanets.Data data = body.data();
    assertThat(data.allPlanets().planets().size()).isEqualTo(60);

    List<String> planets = FluentIterable.from(data.allPlanets().planets())
        .transform(new Function<AllPlanets.Data.AllPlanet.Planet, String>() {
          @Override public String apply(AllPlanets.Data.AllPlanet.Planet planet) {
            return planet.fragments().planetFargment().name();
          }
        }).toList();
    assertThat(planets).isEqualTo(Arrays.asList(("Tatooine, Alderaan, Yavin IV, Hoth, Dagobah, Bespin, Endor, Naboo, "
        + "Coruscant, Kamino, Geonosis, Utapau, Mustafar, Kashyyyk, Polis Massa, Mygeeto, Felucia, Cato Neimoidia, "
        + "Saleucami, Stewjon, Eriadu, Corellia, Rodia, Nal Hutta, Dantooine, Bestine IV, Ord Mantell, unknown, "
        + "Trandosha, Socorro, Mon Cala, Chandrila, Sullust, Toydaria, Malastare, Dathomir, Ryloth, Aleen Minor, "
        + "Vulpter, Troiken, Tund, Haruun Kal, Cerea, Glee Anselm, Iridonia, Tholoth, Iktotch, Quermia, Dorin, "
        + "Champala, Mirial, Serenno, Concord Dawn, Zolan, Ojom, Skako, Muunilinst, Shili, Kalee, Umbara")
        .split("\\s*,\\s*")
    ));

    AllPlanets.Data.AllPlanet.Planet firstPlanet = data.allPlanets().planets().get(0);
    assertThat(firstPlanet.fragments().planetFargment().climates()).isEqualTo(Collections.singletonList("arid"));
    assertThat(firstPlanet.fragments().planetFargment().surfaceWater()).isWithin(1d);
    assertThat(firstPlanet.filmConnection().totalCount()).isEqualTo(5);
    assertThat(firstPlanet.filmConnection().films().size()).isEqualTo(5);
    assertThat(firstPlanet.filmConnection().films().get(0).fragments().filmFragment().title()).isEqualTo("A New Hope");
    assertThat(firstPlanet.filmConnection().films().get(0).fragments().filmFragment().producers()).isEqualTo(Arrays
        .asList("Gary Kurtz", "Rick McCallum"));
  }

  @Test public void errorResponse() throws Exception {
    server.enqueue(mockResponse("src/test/graphql/errorResponse.json"));
    Call<Response<AllPlanets.Data>> call = service.heroDetails(new AllPlanets());
    Response<AllPlanets.Data> body = call.execute().body();
    assertThat(body.isSuccessful()).isFalse();
    //noinspection ConstantConditions
    assertThat(body.errors()).containsExactly(new Error(
        "Cannot query field \"names\" on type \"Species\".",
        Collections.singletonList(new Error.Location(3, 5))));
  }

  @Test public void productsWithDates() throws Exception {
    server.enqueue(mockResponse("src/test/graphql/productsWithDate.json"));

    Call<Response<ProductsWithDate.Data>> call = service.productsWithDate(new ProductsWithDate());
    Response<ProductsWithDate.Data> body = call.execute().body();
    assertThat(body.isSuccessful()).isTrue();

    assertThat(server.takeRequest().getBody().readString(Charsets.UTF_8))
        .isEqualTo("{\"query\":\"query ProductsWithDate {" +
            "  shop {" +
            "    products(first: 10) {" +
            "      edges {" +
            "        node {" +
            "          title" +
            "          createdAt" +
            "        }" +
            "      }" +
            "    }" +
            "  }}\",\"variables\":{}}");

    ProductsWithDate.Data data = body.data();
    assertThat(data.shop().products().edges().size()).isEqualTo(10);

    List<String> dates = FluentIterable.from(data.shop().products().edges())
        .transform(new Function<ProductsWithDate.Data.Shop.Product.Edge, String>() {
          @Override public String apply(ProductsWithDate.Data.Shop.Product.Edge productEdge) {
            return DATE_FORMAT.format(productEdge.node().createdAt());
          }
        }).toList();

    assertThat(dates).isEqualTo(Arrays.asList(
        "2013-11-18T19:35:35Z", "2013-11-18T19:35:40Z", "2013-11-18T19:35:54Z", "2013-11-18T19:35:56Z",
        "2013-11-18T19:36:33Z", "2013-11-18T19:36:45Z", "2013-11-18T19:37:08Z", "2013-11-18T19:37:24Z",
        "2013-11-18T19:37:26Z", "2013-11-18T19:37:28Z"));
  }

  private static MockResponse mockResponse(String fileName) throws IOException {
    return new MockResponse().setBody(Files.toString(new File(fileName), Charsets.UTF_8));
  }
}
