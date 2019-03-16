package com.alok.sampleprojects.dto;

import java.util.List;

/**
 * Created by alokprakash.p on 6/8/2015.
 */
public class DimSearchDTO {
    private List<DimensionDTO> dimension;

    public List<DimensionDTO> getDimension() {
        return dimension;
    }

    public void setDimension(List<DimensionDTO> dimension) {
        this.dimension = dimension;
    }
}