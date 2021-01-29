package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace AddPlacePayLoad(String name, String language, String address){
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setName(name);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        p.setLocation(location);

        return p;
    }
    public String deletePlacePayLoad(String placeValue){
        return "{\n" +
                "    \"place_id\":\""+placeValue+"\" \n" +
                "}";
    }
}
