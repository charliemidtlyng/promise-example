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
                var deferred = when.defer();
                $.ajax({
                    url: '/rest/artists/similarArtists/' + artist.mbid,
                    dataType: 'json',
                    success: function(data) {
                        deferred.resolve(data);
                    },
                    error: function() {
                        deferred.reject();
                    }
                });
                return deferred.promise;
            }

            /** Task 1 **/
            var renderUl = function() {
                    /** Task 1: Make me use deferred with whenjs: https://github.com/cujojs/when/wiki/Examples **/
                    var deferred = when.defer();
                    function appendUl() {
                        $('<ul></ul>').appendTo(controller.elem);
                        deferred.resolve();
                    }
                    setTimeout(function() {
                        appendUl();
                    }, 1500);

                    return deferred.promise;
                };

            // Task 2
            var fetchTopArtists = function() {
                    var deferred = when.defer();
                    $.ajax({
                        url: '/rest/artists',
                        dataType: 'json',
                        success: function(artists) {
                            deferred.resolve(artists);
                        },
                        error: function(dog) {
                            deferred.reject();
                        }

                    });
                    return deferred.promise;
                };


            /** Task 3 **/
            var loadAllSimilarArtists = function(artists) {
                var promiseArray = [];
                _.each(artists, function(artist) {
                    var promise = fetchSimilarArtist(artist);
                    promiseArray.push(promise);
                });
                return when.all(promiseArray);
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
                    renderUl()
                        .then(fetchTopArtists)
                        .then(renderTopArtists)
                        .then(loadAllSimilarArtists)
                        .then(renderSimilarArtists);
                }
            };
        };


    // init function
    return function(elem, options) {
        return Object.create(controller()).init(elem, options);
    };

});