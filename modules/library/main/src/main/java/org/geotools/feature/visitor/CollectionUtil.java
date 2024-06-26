/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2005-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.feature.visitor;

import org.geotools.api.feature.FeatureVisitor;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;

/**
 * @author Cory Horner, Refractions
 * @since 2.2.M2
 */
public class CollectionUtil {
    /**
     * Navigate the collection and call vistor.visit( Feature ) for each element in the collection.
     *
     * @param collection the SimpleFeatureCollection containing the features we want to visit
     * @param visitor the visitor which already knows which attributes it wants to meet
     */
    static void accept(SimpleFeatureCollection collection, FeatureVisitor visitor) {
        try (SimpleFeatureIterator iterator = collection.features()) {
            while (iterator.hasNext()) {
                SimpleFeature feature = iterator.next();
                visitor.visit(feature);
            }
        }
    }

    static void accept(SimpleFeatureCollection collection, FeatureVisitor... visitors) {
        try (SimpleFeatureIterator iterator = collection.features()) {
            while (iterator.hasNext()) {
                SimpleFeature feature = iterator.next();

                for (FeatureVisitor visitor : visitors) {
                    visitor.visit(feature);
                }
            }
        }
    }

    public static Object calc(SimpleFeatureCollection collection, FeatureCalc calculator) {
        accept(collection, calculator);

        return calculator.getResult();
    }
}
