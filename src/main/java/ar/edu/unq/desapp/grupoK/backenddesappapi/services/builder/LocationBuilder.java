package ar.edu.unq.desapp.grupoK.backenddesappapi.services.builder;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Location;

public class LocationBuilder {
    private String name;
    private String province;
    private Integer population;
    private boolean connectivityState;

    public static LocationBuilder locationwithName(String name) {
        LocationBuilder builder = new LocationBuilder();
        builder.name = name;
        return builder;
    }

    public Location build() {
        return new Location(name, province, population, connectivityState);
    }

    public LocationBuilder withProvince(String province) {
        this.province = province;
        return this;
    }

    public LocationBuilder withPopulation(Integer population) {
        this.population = population;
        return this;
    }

    public LocationBuilder withConnectivityState(boolean connectivityState) {
        this.connectivityState = connectivityState;
        return this;
    }
}
