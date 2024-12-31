module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080',  // Spring Boot Backend
                changeOrigin: true
            }
        }
    },
    lintOnSave: false,
    outputDir: '../resources/static',  // Builds Vue into Spring Boot static folder
    indexPath: '../static/index.html'
};
