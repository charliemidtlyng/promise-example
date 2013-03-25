define(['jquery', 'underscore', 'when'], function($, _, when) {

    var controller = function() {

            function addImage(imageUrl) {
                return '<img src=' + imageUrl + ' />';
            }

            function renderTopArtists(artist) {
                _.each(artists, function(artist) {
                    $('<li><span>Navn: ' + artist.name + '</span>' + addImage(artist.image) + '</li>').appendTo('ul');
                });
            }

            var fetchTopArtists = function() {
                    $.ajax({
                        url: '/rest/artists',
                        dataType: 'json',
                        success: function(artists) {
                                renderTopArtists(artist);
                        },
                        error: function(dog) {
                            $('<li>Ooops!</li>').appendTo('ul');
                        }

                    });
                };

            /** Hack below here! **/
            var renderUl = function() {
                    // TODO: Make me use deferred with when
                    function appendUl() {
                        $('<ul></ul>').appendTo(controller.elem);
                    }
                    setTimeout(function() {
                        appendUl();
                    }, 1500);
                };

            // Public functions
            return {
                init: function(elem) {
                    controller.elem = $(elem);
                    return this;
                },
                render: function() {
                    $(controller.elem).empty();
                    // TODO: Run in sequence
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