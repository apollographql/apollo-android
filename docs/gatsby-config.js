const themeOptions = require('gatsby-theme-apollo-docs/theme-options');

module.exports = {
  pathPrefix: '/docs/android',
  plugins: [
    {
      resolve: 'gatsby-theme-apollo-docs',
      options: {
        ...themeOptions,
        root: __dirname,
        subtitle: 'Client (Android)',
        description: 'A guide to using Apollo with Android',
        githubRepo: 'apollographql/apollo-android',
        sidebarCategories: {
          null: [
            'index',
            'essentials/get-started',
          ],
          'Tutorial': [
            'tutorial/00-introduction',
            'tutorial/01-configure-project',
            'tutorial/02-add-the-graphql-schema',
            'tutorial/03-write-your-first-query',
            'tutorial/04-execute-the-query',
            'tutorial/05-connect-queries-to-your-ui',
            'tutorial/06-add-more-info',
            'tutorial/07-paginate-results',
            'tutorial/08-add-a-details-view',
            'tutorial/09-write-your-first-mutation',
            'tutorial/10-authenticate-your-queries',
            'tutorial/11-subscriptions',
          ],
          'Fetching data': [
            'essentials/queries',
            'essentials/mutations',
            'essentials/caching',
            'advanced/persisted-queries',
          ],
          'Languages & Extensions': [
            'advanced/coroutines',
            'advanced/multiplatform',
            'advanced/rxjava2'
          ],
          Reference: [
            'essentials/plugin-configuration',
            'advanced/android',
            'essentials/migration',
            'advanced/no-runtime',
          ],
        }
      }
    }
  ]
};
