define(['jquery', 'underscore', 'when'], function($, _, when) {

    var controller = function() {

            function addImage(imageUrl) {
                return '<img src=' + imageUrl + ' />';
            }

            function renderTopArtists(artists) {
                _.each(artists, function(artist) {
                    $('<li id='+artist.mbid+'><span>Navn: ' + artist.name + '</span>' + addImage(artist.image) + '</li>').appendTo('ul');
                });
                return artists;
            }

            function renderSimilarArtists(allSimilarArtists) {
                _.each(allSimilarArtists, function(similarArtists) {
                    var mbid = similarArtists.artistMbid;
                    _.each(similarArtists.similar, function(artist) {
                        $('<div>Navn: ' + artist.name + ':' + artist.match + '</div>').appendTo('li#' + mbid);
                    });
                });
                return allSimilarArtists;
            }

            function fetchSimilarArtist(artist) {
                return $.ajax({
                    url: '/rest/artists/similarArtists/' + artist.mbid,
                    dataType: 'json'
                });
            }

            /** Task 1 **/
            var renderUl = function() {
                    /** Task 1: Make me use deferred with whenjs: https://github.com/cujojs/when/wiki/Examples **/
                    function appendUl() {
                        $('<ul></ul>').appendTo(controller.elem);
                    }
                    setTimeout(function() {
                        appendUl();
                    }, 1500);
                };

            // Task 2
            var fetchTopArtists = function() {
                    $.ajax({
                        url: '/rest/artists',
                        dataType: 'json',
                        success: function(artists) {
                                renderTopArtists(artists);
                        },
                        error: function(dog) {
                            $('<li>Ooops!</li>').appendTo('ul');
                        }

                    });
                };


            /** Task 3 **/
            var loadAllSimilarArtists = function(artists) {
                // TODO: Implement me with when all
            };

            // Public functions
            return {
                init: function(elem) {
                    controller.elem = $(elem);
                    return this;
                },
                render: function() {
                    $(controller.elem).empty();
                    /** Task 1-3 **/
                    renderUl();
                    fetchTopArtists();
                }
            };
        };


    // init function
    return function(elem, options) {
        return Object.create(controller()).init(elem, options);
    };

});