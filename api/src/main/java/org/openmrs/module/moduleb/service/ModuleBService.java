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
package org.openmrs.module.moduleb.service;

import javax.ws.rs.core.Response;

import org.openmrs.api.APIException;

/**
 * Service interface for communication.
 * 
 * @author Steve McKee
 */
public interface ModuleBService {

	public Response exportFile(String host, String token, String record, String field, String event, 
	                              Integer repeatInstance, String returnFormat) throws APIException;
}
