function Router(routes) {
    this.constructor(routes);
}

Router.prototype = {

    routes: null,

    constructor: function (routes) {
        this.routes = routes;
    },

    loadLocation: function (url) {
        let pageService = new PageViewService(document.getElementById("entity"))
        let replaceURL = url.replace("http://localhost:8080/", "")
        let idRegexp = new RegExp('[0-9]+')
        for (let route of this.routes) {
            let regexp = new RegExp(route.path)
            if (regexp.test(replaceURL)) {
                if (idRegexp.test(replaceURL)) {
                    let requestURL = route.url + replaceURL.match(idRegexp)
                    pageService.renderPage(route, requestURL)

                } else {
                    pageService.renderPage(route, route.url)

                }
            }
        }

    },

    changeLocation: function (document) {
        window.history.pushState("", "", document.href)
    }

}