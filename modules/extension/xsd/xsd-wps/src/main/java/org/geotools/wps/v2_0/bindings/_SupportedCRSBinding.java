package org.geotools.wps.v2_0.bindings;

import javax.xml.namespace.QName;
import net.opengis.wps20.Wps20Factory;
import org.geotools.wps.v2_0.WPS;
import org.geotools.xsd.AbstractComplexEMFBinding;

/**
 * Binding object for the type http://www.opengis.net/wps/2.0:_SupportedCRS.
 *
 * <p>
 *
 * <pre>
 *  <code>
 *  &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;complexType name="_SupportedCRS" xmlns="http://www.w3.org/2001/XMLSchema"&gt;
 *
 *  			&lt;simpleContent&gt;
 *
 *  				&lt;extension base="anyURI"&gt;
 *
 *  					&lt;attribute name="default" type="boolean" use="optional"/&gt;
 *
 *  				&lt;/extension&gt;
 *
 *  			&lt;/simpleContent&gt;
 *
 *  		&lt;/complexType&gt;
 *
 *   </code>
 *  </pre>
 *
 * @generated
 */
public class _SupportedCRSBinding extends AbstractComplexEMFBinding {

    public _SupportedCRSBinding(Wps20Factory factory) {
        super(factory);
    }

    /** @generated */
    public QName getTarget() {
        return WPS._SupportedCRS;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    public Class getType() {
        return super.getType();
    }
}
