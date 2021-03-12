/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2021, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.wps.v2_0.bindings;

import javax.xml.namespace.QName;
import net.opengis.wps20.Wps20Factory;
import org.geotools.wps.v2_0.WPS;
import org.geotools.xsd.AbstractComplexEMFBinding;

/**
 * Binding object for the type http://www.opengis.net/wps/2.0:_BoundingBoxData.
 *
 * <p>
 *
 * <pre>
 *  <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_BoundingBoxData" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *
 *  			&lt;complexContent&gt;
 *
 *  				&lt;extension base="wps:DataDescriptionType"&gt;
 *
 *  					&lt;sequence&gt;
 *
 *  						&lt;annotation&gt;
 *
 *  							&lt;documentation&gt;
 *
 *  								Identifies the default CRS that will be used
 *
 *  								unless the Execute operation request specifies
 *
 *  								another supported CRS.
 *
 *  							&lt;/documentation&gt;
 *
 *  						&lt;/annotation&gt;
 *
 *  						&lt;element maxOccurs="unbounded" ref="wps:SupportedCRS"/&gt;
 *
 *  					&lt;/sequence&gt;
 *
 *  				&lt;/extension&gt;
 *
 *  			&lt;/complexContent&gt;
 *
 *  		&lt;/complexType&gt;
 *
 *   </code>
 *  </pre>
 *
 * @generated
 */
public class _BoundingBoxDataBinding extends AbstractComplexEMFBinding {

    public _BoundingBoxDataBinding(Wps20Factory factory) {
        super(factory);
    }

    /** @generated */
    @Override
    public QName getTarget() {
        return WPS._BoundingBoxData;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Class getType() {
        return super.getType();
    }
}
