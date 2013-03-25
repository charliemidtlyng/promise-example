package no.bekk.promise.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.Serializable;

@Controller
@RequestMapping("/artists")
public class ApiController {

	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody Artist getArtists(@PathVariable String name) {
		Artist artist = new Artist();
		artist.name = "lalala";
		
		return artist;
	}

	@RequestMapping(value = "/similarArtists/{id}", method = RequestMethod.GET)
	public @ResponseBody Artist getSimilarArtist(@PathVariable String id) {

		Artist artist = new Artist();
		artist.name = "lalala";
		
		return artist;

	}


	private class Artist implements Serializable {

		public String name;
		public String mbid;
		public String url;
		public String image;
		public Double match;
		
	}
	
}