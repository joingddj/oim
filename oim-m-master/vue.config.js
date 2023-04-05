module.exports = {
  configureWebpack: {
      module: {
          rules: [{
              test: /\.less$/,
              use: [{
                  loader: "less-loader", options: {
                      lessOptions: {
                          javascriptEnabled: true
                      }
                  },
              }],
          }],
      },
      // Configuration applied to all builds
  },
  // 这里是配置上线读取当前目录，默认是根路径，如 /js, /css 等，具体根据项目来
  publicPath: process.env.NODE_ENV === "production" ? "./" : "/",
  transpileDependencies: [
      "vuetify",
  ],
};