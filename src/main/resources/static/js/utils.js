const Utils = {

    loadTemplate: function (url) {
        let path = "/view/" + url
        return fetch(path
        ).then(response => {
            if (response.ok) {
                return response.text()
            }
        })
    },

    loadData: function (method, url) {
        return fetch(url, {
            method: method
        })
            .then(response => {
                if (response.ok) {
                    if (response.status !== 204) {
                        return response.json()
                    } else {
                        return response.status
                    }
                }
                return response.json().then(error => {
                    throw  error
                })
            })
    },

    loadDataWithBody: function (method, url, body) {
        return fetch(url, {

            method: method,
            body: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(response => {
                    if (response.ok) {
                        return response.status
                    }
                    return response.json().then(error => {
                        throw  error
                    })
                }
            )
    }


}