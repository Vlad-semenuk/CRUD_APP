function PageViewService(rootElement) {
    this.constructor(rootElement)
}

PageViewService.prototype = {

    rootElement: null,

    constructor: function (rootElement) {
        this.rootElement = rootElement
    },

    renderPage: function (route, url) {

        route.controller(url, this.rootElement)

    }
}
