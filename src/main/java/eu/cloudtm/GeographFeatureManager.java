package eu.cloudtm;

import org.infinispan.dataplacement.c50.keyfeature.Feature;
import org.infinispan.dataplacement.c50.keyfeature.FeatureValue;
import org.infinispan.dataplacement.c50.keyfeature.NumericFeature;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pedro Ruivo
 * @since 1.0
 */
public class GeographFeatureManager extends CloudtmFeatureManager {

    private static final Feature[] GEOGRAPH_FEATURES = new Feature[]{
            new NumericFeature("x"),
            new NumericFeature("y")
    };

    @Override
    public Feature[] getAllKeyFeatures() {
        return GEOGRAPH_FEATURES;
    }

    @Override
    protected Map<Feature, FeatureValue> getFeatureFromGroup(String group) {
        if (group == null || group.isEmpty()) {
            return Collections.emptyMap();
        }
        String[] coordinates = group.split("|");
        if (coordinates.length < 2) {
            return Collections.emptyMap();
        }
        Map<Feature, FeatureValue> map = new HashMap<Feature, FeatureValue>();
        map.put(GEOGRAPH_FEATURES[0], GEOGRAPH_FEATURES[0].featureValueFromParser(coordinates[0]));
        map.put(GEOGRAPH_FEATURES[1], GEOGRAPH_FEATURES[1].featureValueFromParser(coordinates[1]));
        return map;
    }
}
