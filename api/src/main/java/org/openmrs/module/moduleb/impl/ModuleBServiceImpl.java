/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.moduleb.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.module.moduleb.request.ExportFileRequest;
import org.openmrs.module.moduleb.service.ModuleBService;
import org.openmrs.module.moduleb.util.ModuleBConstants;


/**
 * Service implementation for Module B.
 * 
 * @author Steve McKee
 */
public class ModuleBServiceImpl implements ModuleBService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private Client client = ClientBuilder.newClient();
	
	/**
	 * Constructor method
	 */
	public ModuleBServiceImpl() {
	}
	
	/**
	 * @see org.openmrs.module.moduleb.service.ModuleBService#exportFile(java.lang.String, java.lang.String, 
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public Response exportFile(String host, String token, String record, String field, String event, Integer repeatInstance, 
	                           String returnFormat) throws APIException {
		WebTarget exportFileTarget = client.target(host);
		ExportFileRequest exportFileRequest = new ExportFileRequest(token, record, field, event, repeatInstance);
		if (StringUtils.isNotBlank(returnFormat)) {
			exportFileRequest.setReturnFormat(returnFormat);
		}
		
		Form form = exportFileRequest.createRequestForm();
		Response exportFileResponse = null;
		try {
			exportFileResponse = exportFileTarget.request().header(
				ModuleBConstants.HEADER_CONTENT_TYPE, ModuleBConstants.CONTENT_TYPE_FORM).post(Entity.form(form));
		} catch (Exception e) {
			log.error("Error exporting file.", e);
			throw new APIException(e);
		}
		
		return exportFileResponse;
	}
}
