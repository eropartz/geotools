/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2007-2011, Open Source Geospatial Foundation (OSGeo)
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

package org.geotools.data.complex.feature.xpath;

import java.util.Locale;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.NodePointerFactory;
import org.geotools.api.feature.Attribute;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.feature.type.AttributeType;
import org.geotools.api.feature.type.ComplexType;

/**
 * A node factory which creates special node pointers featurs.
 *
 * <p>The following types are supported:
 *
 * <ul>
 *   <li>{@link Attribute}
 *   <li>{@link AttributeType}
 * </ul>
 *
 * @author Justin Deoliveira (The Open Planning Project)
 * @author Gabriel Roldan (Axios Engineering)
 */
public class AttributeNodePointerFactory implements NodePointerFactory {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public NodePointer createNodePointer(QName name, Object object, Locale locale) {

        /*
         * Do not handle SimpleFeature, which should be handled by FeatureNodeFactory, registered by
         * XPathPropertyAccessorFactory in gt-xsd-core. See GEOS-3525.
         */
        if (object instanceof Attribute && !(object instanceof SimpleFeature)) {
            return new AttributeNodePointer(null, (Attribute) object, name);
        }

        if (object instanceof ComplexType && !(object instanceof SimpleFeatureType)) {
            return new FeatureTypePointer(null, (ComplexType) object, name);
        }

        return null;
    }

    @Override
    public NodePointer createNodePointer(NodePointer parent, QName name, Object object) {

        /*
         * Do not handle SimpleFeature, which should be handled by FeatureNodeFactory, registered by
         * XPathPropertyAccessorFactory in gt-xsd-core. See GEOS-3525.
         */
        if (object instanceof Attribute && !(object instanceof SimpleFeature)) {
            return new AttributeNodePointer(parent, (Attribute) object, name);
        }

        if (object instanceof ComplexType && !(object instanceof SimpleFeatureType)) {
            return new FeatureTypePointer(null, (ComplexType) object, name);
        }

        return null;
    }
}
