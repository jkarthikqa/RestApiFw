package resources;

public enum ApiResources {
    Add_Place_Api("/maps/api/place/add/json"),
    Update_Place_Api("/maps/api/place/update/json"),
    Get_Place_Api("/maps/api/place/get/json"),
    Delete_Place_Api("/maps/api/place/delete/json");

    private String resource;
    ApiResources(String resource){
    this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
