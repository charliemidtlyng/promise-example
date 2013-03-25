package no.bekk.promise.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/artists")
public class ApiController {

    Map<String, SimilarArtists> similarArtistsMap = new HashMap<String, SimilarArtists>();

    public ApiController() {
        populateSimilarArtists();
    }

    @RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public List<Artist> getArtists() {
        List<Artist> topArtists = Arrays.asList(new Artist[]{
                new Artist("Kaizers Orchestra", "2c3bac0a-5158-442f-b50b-056aae52138c", "http://www.last.fm/music/Kaizers+Orchestra", "http://userserve-ak.last.fm/serve/252/50811391.jpg", 0.0),
                new Artist("Queen","0383dadf-2a4e-4d10-a46a-e9e041da8eb3","http://www.last.fm/music/Queen", "http://userserve-ak.last.fm/serve/252/135338.jpg", 0.0),
                new Artist("Jay-Z","f82bcf78-5b69-4622-a5ef-73800768d9ac","http://www.last.fm/music/Jay-Z", "http://userserve-ak.last.fm/serve/252/3733348.jpg", 0.0),
        });

        return topArtists;
	}

	@RequestMapping(value = "/similarArtists/{id}", method = RequestMethod.GET)
	@ResponseBody
    public SimilarArtists getSimilarArtist(@PathVariable String id) {
        System.out.println(similarArtistsMap.get(id));
        return similarArtistsMap.get(id);
	}

    private void populateSimilarArtists() {

        similarArtistsMap.put("0383dadf-2a4e-4d10-a46a-e9e041da8eb3",
                new SimilarArtists("0383dadf-2a4e-4d10-a46a-e9e041da8eb3",
                        Arrays.asList(new Artist[]{
                                new Artist("Freddie Mercury", "022589ac-7177-460d-a178-9976cf70e29f", "www.last.fm/music/Freddie+Mercury","http://userserve-ak.last.fm/serve/34/294433.jpg", 1.0),
                                new Artist("Brian May", "0dc6fd4e-d676-44f1-baf3-8c28617ed87b", "www.last.fm/music/Brian+May","http://userserve-ak.last.fm/serve/34/309348.jpg", 0.677938),
                                new Artist("Queen + Paul Rodgers", "9dcffd99-eca6-4a09-8dbd-c6c4ed2f20fd", "www.last.fm/music/Queen+%252B+Paul+Rodgers","http://userserve-ak.last.fm/serve/34/143367.jpg", 0.558724)
                        })));
        similarArtistsMap.put("2c3bac0a-5158-442f-b50b-056aae52138c",
                new SimilarArtists("2c3bac0a-5158-442f-b50b-056aae52138c",
                        Arrays.asList(new Artist[]{
                                new Artist("Skambankt", "e26531c7-45c5-4637-a9fe-d088b93a300f", "www.last.fm/music/Skambankt","http://userserve-ak.last.fm/serve/34/325424.jpg", 1.0),
                                new Artist("gnom", "5dd11b94-1742-4de8-b278-a49affc01e17", "www.last.fm/music/gnom","http://userserve-ak.last.fm/serve/34/365263.jpg", 0.960553),
                                new Artist("Janove Ottesen", "436227b6-870d-4c50-ab78-73e603c91ae2", "www.last.fm/music/Janove+Ottesen","http://userserve-ak.last.fm/serve/34/34956.jpg", 0.905744)
                        })));
        similarArtistsMap.put("f82bcf78-5b69-4622-a5ef-73800768d9ac",
                new SimilarArtists("f82bcf78-5b69-4622-a5ef-73800768d9ac",
                        Arrays.asList(new Artist[]{
                                new Artist("Kanye West", "164f0d73-1234-4e2c-8743-d77bf2191051", "www.last.fm/music/Kanye+West","http://userserve-ak.last.fm/serve/34/294433.jpg", 1.0),
                                new Artist("R. Kelly & Jay-Z", "", "www.last.fm/music/R.+Kelly+&+Jay-Z","http://userserve-ak.last.fm/serve/34/62503751.jpg", 0.739864),
                                new Artist("Nas", "5e9c618a-437d-4cca-9957-4a33cba93da2", "www.last.fm/music/Nas","http://userserve-ak.last.fm/serve/34/46358275.jpg", 0.691355)
                        })));

    }

    private class Artist implements Serializable {

		public String name;
		public String mbid;
		public String url;
		public String image;
		public Double match;

        private Artist() {

        }

        private Artist(String name, String mbid, String url, String image, Double match) {
            this.name = name;
            this.mbid = mbid;
            this.url = url;
            this.image = image;
            this.match = match;
        }

        @Override
        public String toString() {
            return "Artist{" +
                    "name='" + name + '\'' +
                    ", mbid='" + mbid + '\'' +
                    ", url='" + url + '\'' +
                    ", image='" + image + '\'' +
                    ", match=" + match +
                    '}';
        }
    }

    private class SimilarArtists implements Serializable {
        @Override
        public String toString() {
            return "SimilarArtists{" +
                    "artistMbid='" + artistMbid + '\'' +
                    ", similar=" + similar +
                    '}';
        }

        public String artistMbid;
		public List<Artist> similar;

        private SimilarArtists() {

        }

        private SimilarArtists(String artistMbid, List<Artist> similar) {
            this.artistMbid = artistMbid;
            this.similar = similar;
        }
    }


}