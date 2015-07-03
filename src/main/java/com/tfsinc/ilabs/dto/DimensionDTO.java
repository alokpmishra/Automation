package com.tfsinc.ilabs.dto;

import java.util.List;

/**
 * Created by alokprakash.p on 6/8/2015.
 */
public class DimensionDTO {
    private String name;
    private List<String> paths;

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
