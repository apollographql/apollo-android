package com.example.mutation_create_review_semantic_naming.type

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.InputFieldMarshaller
import com.apollographql.apollo.api.InputFieldWriter
import com.apollographql.apollo.api.InputType
import java.lang.Object
import javax.annotation.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

/**
 * The input object sent when someone is creating a new review
 */
@Generated("Apollo GraphQL")
@Suppress("NAME_SHADOWING", "LocalVariableName")
class ReviewInput(
    /**
     * 0-5 stars
     */
    val stars: Int,
    /**
     * for test purpose only
     */
    val nullableIntFieldWithDefaultValue: Input<Int> = Input.optional(10),
    /**
     * Comment about the movie, optional
     */
    val commentary: Input<String> = Input.optional(null),
    /**
     * Favorite color, optional
     */
    val favoriteColor: ColorInput,
    /**
     * for test purpose only
     */
    val enumWithDefaultValue: Input<Episode> = Input.optional(Episode.safeValueOf("JEDI")),
    /**
     * for test purpose only
     */
    val nullableEnum: Input<Episode> = Input.optional(null),
    /**
     * for test purpose only
     */
    val listOfCustomScalar: Input<List<Object?>> = Input.optional(null),
    /**
     * for test purpose only
     */
    val customScalar: Input<Object> = Input.optional(null),
    /**
     * for test purpose only
     */
    val listOfEnums: Input<List<Episode?>> = Input.optional(null),
    /**
     * for test purpose only
     */
    val listOfInt: Input<List<Int?>> = Input.optional(listOf(1, 2, 3)),
    /**
     * for test purpose only
     */
    val listOfString: Input<List<String?>> = Input.optional(listOf("test1", "test2", "test3")),
    /**
     * for test purpose only
     */
    val booleanWithDefaultValue: Input<Boolean> = Input.optional(true),
    /**
     * for test purpose only
     */
    val listOfListOfString: Input<List<List<String?>?>> = Input.optional(null),
    /**
     * for test purpose only
     */
    val listOfListOfEnum: Input<List<List<Episode?>?>> = Input.optional(null),
    /**
     * for test purpose only
     */
    val listOfListOfCustom: Input<List<List<Object?>?>> = Input.optional(null),
    /**
     * for test purpose only
     */
    val listOfListOfObject: Input<List<List<ColorInput?>?>> = Input.optional(null),
    /**
     * for test purpose only
     */
    val capitalizedField: Input<String> = Input.optional(null)
) : InputType {
    override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller { writer ->
        writer.writeInt("stars", stars)
        if (nullableIntFieldWithDefaultValue.defined)
                writer.writeInt("nullableIntFieldWithDefaultValue",
                nullableIntFieldWithDefaultValue.value)
        if (commentary.defined) writer.writeString("commentary", commentary.value)
        writer.writeObject("favoriteColor", favoriteColor.marshaller())
        if (enumWithDefaultValue.defined) writer.writeString("enumWithDefaultValue",
                enumWithDefaultValue.value?.rawValue)
        if (nullableEnum.defined) writer.writeString("nullableEnum", nullableEnum.value?.rawValue)
        if (listOfCustomScalar.defined) {
            writer.writeList("listOfCustomScalar", listOfCustomScalar.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeCustom(CustomType.DATE, value)
                    }
                }
            })
        }
        if (customScalar.defined) writer.writeCustom("customScalar", CustomType.DATE,
                customScalar.value)
        if (listOfEnums.defined) {
            writer.writeList("listOfEnums", listOfEnums.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeString(value?.rawValue)
                    }
                }
            })
        }
        if (listOfInt.defined) {
            writer.writeList("listOfInt", listOfInt.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeInt(value)
                    }
                }
            })
        }
        if (listOfString.defined) {
            writer.writeList("listOfString", listOfString.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeString(value)
                    }
                }
            })
        }
        if (booleanWithDefaultValue.defined) writer.writeBoolean("booleanWithDefaultValue",
                booleanWithDefaultValue.value)
        if (listOfListOfString.defined) {
            writer.writeList("listOfListOfString", listOfListOfString.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeList{ listItemWriter-> 
                            value?.forEach { value ->
                                listItemWriter.writeString(value)
                            }
                        }
                    }
                }
            })
        }
        if (listOfListOfEnum.defined) {
            writer.writeList("listOfListOfEnum", listOfListOfEnum.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeList{ listItemWriter-> 
                            value?.forEach { value ->
                                listItemWriter.writeString(value?.rawValue)
                            }
                        }
                    }
                }
            })
        }
        if (listOfListOfCustom.defined) {
            writer.writeList("listOfListOfCustom", listOfListOfCustom.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeList{ listItemWriter-> 
                            value?.forEach { value ->
                                listItemWriter.writeCustom(CustomType.DATE, value)
                            }
                        }
                    }
                }
            })
        }
        if (listOfListOfObject.defined) {
            writer.writeList("listOfListOfObject", listOfListOfObject.value?.let { value ->
                InputFieldWriter.ListWriter { listItemWriter ->
                    value.forEach { value ->
                        listItemWriter.writeList{ listItemWriter-> 
                            value?.forEach { value ->
                                listItemWriter.writeObject(value?.marshaller())
                            }
                        }
                    }
                }
            })
        }
        if (capitalizedField.defined) writer.writeString("CapitalizedField", capitalizedField.value)
    }
}
