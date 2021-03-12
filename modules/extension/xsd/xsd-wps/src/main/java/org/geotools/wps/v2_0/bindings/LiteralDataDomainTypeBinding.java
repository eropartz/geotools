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
import net.opengis.wps20.LiteralDataDomainType;
import net.opengis.wps20.Wps20Factory;
import org.geotools.wps.v2_0.WPS;
import org.geotools.xsd.AbstractComplexEMFBinding;

/**
 * Binding object for the type http://www.opengis.net/wps/2.0:LiteralDataDomainType.
 *
 * <p>
 *
 * <pre>
 *  <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="LiteralDataDomainType" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *
 *  		&lt;annotation&gt;
 *
 *  			&lt;documentation&gt;
 *
 *  				A literal data domain consists of a value type and range,
 *
 *  				and optionally a unit of measurement and a default value.
 *
 *  			&lt;/documentation&gt;
 *
 *  		&lt;/annotation&gt;
 *
 *  		&lt;sequence&gt;
 *
 *  			&lt;choice&gt;
 *
 *  				&lt;element ref="ows:AllowedValues"/&gt;
 *
 *  				&lt;element ref="ows:AnyValue"/&gt;
 *
 *  				&lt;element ref="ows:ValuesReference"/&gt;
 *
 *  			&lt;/choice&gt;
 *
 *  			&lt;element minOccurs="0" ref="ows:DataType"/&gt;
 *
 *  			&lt;element minOccurs="0" ref="ows:UOM"/&gt;
 *
 *  			&lt;element minOccurs="0" ref="ows:DefaultValue"/&gt;
 *
 *  		&lt;/sequence&gt;
 *
 *  	&lt;/complexType&gt;
 *
 *   </code>
 *  </pre>
 *
 * @generated
 */
public class LiteralDataDomainTypeBinding extends AbstractComplexEMFBinding {

    public LiteralDataDomainTypeBinding(Wps20Factory factory) {
        super(factory);
    }

    /** @generated */
    @Override
    public QName getTarget() {
        return WPS.LiteralDataDomainType;
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
        return LiteralDataDomainType.class;
    }
}
