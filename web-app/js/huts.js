//   Copyright 2008 Chris Searle
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

function deleteCheckSubmit() {

    if (confirm('Are you sure?')) {
        document.forms['deletemenuform'].submit();
    } else {
        return false;
    }
}

function HutLocation(name, lat, lng, imgurl, showurl, linktext, description, organization, huttype) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
    this.showurl = showurl;
    this.imgurl = imgurl;
    this.linktext = linktext;
    this.description = description;
    this.organization = organization;
    this.huttype = huttype;
}

function initializeMaps(hutlocs) {
    //    var norway = new GLatLng(64.830253743883, 16.2158203125);
    var norway = new GLatLng(60, 10.7);

    if (GBrowserIsCompatible()) {
        var map = new google.maps.Map2(document.getElementById("map"));

        map.setCenter(norway, 7);
        map.addMapType(G_PHYSICAL_MAP);

        map.addControl(new GLargeMapControl());
        map.addControl(new GMapTypeControl(), new GControlPosition(G_ANCHOR_BOTTOM_RIGHT, 0));
    } else {
        toggleMapList();
    }

    var seenHut = false;

    var markersArray = [];

    for (name in hutlocs) {
        seenHut = true;

        var hutloc = hutlocs[name];

        var marker = getMarker(hutloc);

        markersArray.push(marker);
    }

    if (seenHut) {
        var cluster = new ClusterMarker(map, { markers:markersArray, clusterMarkerIcon: getClusterIcon() });
        cluster.fitMapToMarkers();
    } else {
        $('#nomapwarning').toggle();
    }

    return map;
}


function initializeSingleMap(lat, lng, huttype) {
    var point = new GLatLng(lat, lng);

    if (GBrowserIsCompatible()) {
        var map = new google.maps.Map2(document.getElementById("map"));

        map.setCenter(point, 7);

        map.addControl(new GSmallMapControl());
        map.addControl(new GMapTypeControl());
    }

    if ($('#hutimg').length) {
        $('#hutimg, #map').toggle();
    }

    var markerOptions = { clickable: false, icon: getIcon(huttype) };

    var marker = new GMarker(point, markerOptions);

    map.addOverlay(marker);
}

function getMarker(hutloc) {
    var markerOptions = { icon:getIcon(hutloc.huttype) };

    var latlng = new GLatLng(hutloc.lat, hutloc.lng);
    var marker = new GMarker(latlng, markerOptions);

    var popup = $('<h4>').html(hutloc.name);

    if (hutloc.organization) {
        popup.append($('<h5>').html(hutloc.organization));
    }

    if (hutloc.imgurl) {
        popup.append($('<img>').attr('width', '150px').attr('src', hutloc.imgurl));
    }

    popup.append($('<p>').addClass('maptext').html(hutloc.description));

    popup.append($('<p>').addClass('maptext').append($('<a>').attr('href', hutloc.showurl).html(hutloc.linktext)));

    GEvent.addListener(marker, "click", function() {
        // Uses a div to contain the popup - since we need to call html() which gets the _contents_ of a container
        marker.openInfoWindowHtml($('<div>').append(popup).html());
    });

    return marker;
}

function getIcon(huttype) {

    var primaryColor = "#FF0000";

    if (huttype == "PRIVATE") {
        primaryColor = "#0000FF";
    }
    if (huttype == "OWNER") {
        primaryColor = "#FFFF00";
    }

    var newIcon = MapIconFactory.createMarkerIcon({width: 30, height: 30, primaryColor: primaryColor});

    return newIcon;
}

function getClusterIcon() {
    var clusterIcon = MapIconFactory.createMarkerIcon({width: 40, height: 40, primaryColor: "#ccccff"});

    return clusterIcon;
}

function setupKey(icons) {
    var tablediv = $('<div>').attr('id', 'key');

    var table = $('<table>').addClass('key');

    tablediv.append(table);

    for (id in icons) {
        var col1 = $('<td>').append($('<img>').attr('src', icons[id].url));
        var col2 = $('<td>').html(icons[id].desc);

        table.append($('<tr>').append(col1).append(col2));
    }

    // Uses a div to contain the popup - since we need to call html() which gets the _contents_ of a container
    $("#mapkey").css({position: "absolute", padding: "10px", margin: "40px", 'margin-left': "50px"}).html($('<div>').append(tablediv).html());
}

function MapKey(url, desc) {
    this.url = url;
    this.desc = desc;
}
