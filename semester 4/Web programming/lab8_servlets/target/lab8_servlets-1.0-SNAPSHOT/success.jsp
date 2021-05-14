<%@ page import="webubb.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: gutan
  Date: 5/11/2021
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Routes</title>
    <style>
        .asset-name {
            background-color: cornflowerblue;
            border-right: solid 1px black;
        }
    </style>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>

</head>
<body>
<%! User user; %>
<%  user = (User) session.getAttribute("user");
    if (user != null) {
        out.println("Welcome "+user.getUsername());
%>
<br/>
<p><button id="getAssetsbtn" type="button">Get Possible Starting Cities</button></p>
<section><table id="asset-table"></table></section>

<br><br>
<section id="update-result-section"></section>

<br><br>
<p><button style="display: none;" id="finalRoute" type="button">Final Destination</button></p>
<section><table id="city-table"></table></section>

<script>
    function nextNeighbours(cityid) {
        getNeighbours(cityid, function(cities) {
            $("#asset-table").html("");
            $("#asset-table").append("<tr style='background-color: mediumseagreen'><td>Id</td><td>Description</td><td style='background-color: white'></td></tr>");
            for(var name in cities) {
                $("#asset-table").append("<tr><td class='asset-name'>" + cities[name].id + "</td>" +
                    "<td>" + cities[name].name + "</td> + " +
                    "<td><button class='addRoute' type='button' onclick='addRouteFunction(" + cities[name].id + ")'>Add To Route</button></td></tr>");
            }
        })
    }

    function addRouteFunction(cityid) {
        addToRoute(<%= user.getId() %>, cityid, function(response) {
            $("#update-result-section").html(response);
        })
        $("#getAssetsbtn").html("Next City on the Route");
        $("#finalRoute").css("display", "block");
        nextNeighbours(cityid);
    }

    function changeRoute(citypos) {
        routeChange(citypos, function (response) {
            $("#update-result-section").html(response);
        });
        reprintRoute();
        getNewNeighbours();
    }

    function getNewNeighbours() {
        getNeighboursWithoutId(function(cities) {
            $("#asset-table").html("");
            $("#asset-table").append("<tr style='background-color: mediumseagreen'><td>Id</td><td>Description</td><td style='background-color: white'></td></tr>");
            for(var name in cities) {
                $("#asset-table").append("<tr><td class='asset-name'>" + cities[name].id + "</td>" +
                    "<td>" + cities[name].name + "</td> + " +
                    "<td><button class='addRoute' type='button' onclick='addRouteFunction(" + cities[name].id + ")'>Add To Route</button></td></tr>");
            }
        })
    }

    function reprintRoute() {
        getFinalRoute(function(cities) {
            console.log(cities);
            $("#city-table").html("");
            $("#city-table").append("<tr style='background-color: mediumseagreen'><td>Id</td><td>Description</td><td style='background-color: white'></td></tr>");
            var i = 0;
            for(var name in cities) {
                i = i + 1;
                $("#city-table").append("<tr><td class='asset-name'>"+ i +"</td>" +
                    "<td>"+cities[name].name+"</td> + " +
                    "<td><button class='changeRoute' type='button' onclick='changeRoute("+i+")'>Change Route</button></td></tr>");
            }
        })
    }

    $(document).ready(function(){
        $("#getAssetsbtn").click(function() {
            getAllCities(<%= user.getId() %>, function(assets) {
                $("#asset-table").html("");
                $("#asset-table").append("<tr style='background-color: mediumseagreen'><td>Id</td><td>Description</td><td style='background-color: white'></td></tr>");
                for(var name in assets) {
                    $("#asset-table").append("<tr><td class='asset-name'>"+assets[name].id+"</td>" +
                        "<td>"+assets[name].name+"</td> + " +
                        "<td><button class='addRoute' type='button' onclick='addRouteFunction("+assets[name].id+")'>Add To Route</button></td></tr>");
                }
            })
        })

        $("#finalRoute").click(function() {
            getFinalRoute(function(cities) {
                $("#city-table").html("");
                $("#city-table").append("<tr style='background-color: mediumseagreen'><td>Id</td><td>Description</td><td style='background-color: white'></td></tr>");
                var i = 0;
                for(var name in cities) {
                    i = i + 1;
                    $("#city-table").append("<tr><td class='asset-name'>"+ i +"</td>" +
                        "<td>"+cities[name].name+"</td> + " +
                        "<td><button class='changeRoute' type='button' onclick='changeRoute("+i+")'>Change Route</button></td></tr>");
                }
            })
        })
    });
</script>
<%
    }
%>

</body>
</html>

