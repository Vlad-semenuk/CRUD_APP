function Route(path, url, controller) {
    this.constructor(path, url, controller);
}


Route.prototype = {

    path: null,
    url: null,
    controller: null,

    constructor: function (path, url, controller) {
        this.path = path
        this.url = url
        this.controller = controller

    }
}