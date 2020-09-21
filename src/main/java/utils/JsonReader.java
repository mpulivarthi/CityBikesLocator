package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.AppConstants;
import dto.LocationDTO;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonReader {
    public static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static JsonReader INSTANCE;
    static LocationDTO dto;
    private JsonReader(){

    }
    public static synchronized JsonReader getInstance(){
        if(INSTANCE == null)
            INSTANCE = new JsonReader();
        return INSTANCE;
    }
    public void loadTestData(){
        try {
            File file = new File(Objects.requireNonNull(
                    getClass().getClassLoader()
                            .getResource(AppConstants.DATA_FILE_NAME)).getFile());
            dto = mapper.readValue(file, LocationDTO.class);
            System.out.println(dto.getCity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public LocationDTO getLocationDTO(){
        if (dto == null)
            loadTestData();
        return dto;
    }
    public LocationDTO processJsonResponse(List<LinkedHashMap<String, Map<String, Object>>> list, String city){
        Map<String, Object> rMap  = null;
        LocationDTO dto = new LocationDTO();
        for (LinkedHashMap<String, Map<String, Object>> k : list) {
            Map<String, Object> lp = k.get(AppConstants.LOCATION);
            if(lp.containsValue(city)){
                rMap = lp;
                break;
            }
        }
        assert rMap != null;
        assert rMap.get(AppConstants.CITY).toString() != null;
        assert rMap.get(AppConstants.COUNTRY).toString() != null;
        assert (Float)rMap.get(AppConstants.LATITUDE) != null;
        assert (Float)rMap.get(AppConstants.LONGITUDE) != null;
        dto.setCity(rMap.get(AppConstants.CITY).toString());
        dto.setCountry(rMap.get(AppConstants.COUNTRY).toString());
        dto.setLatitude((Float) rMap.get(AppConstants.LATITUDE));
        dto.setLongitude((Float) rMap.get(AppConstants.LONGITUDE));
        return dto;
    }
    public ObjectMapper getMapper(){
        return mapper;
    }
}
