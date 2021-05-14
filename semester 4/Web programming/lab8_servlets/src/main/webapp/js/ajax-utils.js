function getAllCities(userid, callBackFunction) {
    $.getJSON(
        "CityController",
        {action: 'getAll', userid: userid},
        callBackFunction
    );
}

function addToRoute(userid, cityid, callBackFunction) {
    $.get(
        "CityController",
        {
            action: "addToRoute",
            userid: userid,
            cityid: cityid
        },
        callBackFunction
    );
}

function getNeighbours(cityid, callBackFunction) {
    $.getJSON(
        "CityController",
        {action: 'getNeighbours', cityid: cityid},
        callBackFunction
    );
}

function getFinalRoute(callBackFunction) {
    $.getJSON(
        "CityController",
        {action: 'getRoute'},
        callBackFunction
    );
}

function routeChange(citypos, callBackFunction) {
    $.get(
        "CityController",
        {
            action: "changeRoute",
            citypos: citypos
        },
        callBackFunction
    );
}

function getNeighboursWithoutId(callBackFunction) {
    $.getJSON(
        "CityController",
        {action: 'getNeighboursWithoutId'},
        callBackFunction
    );
}
