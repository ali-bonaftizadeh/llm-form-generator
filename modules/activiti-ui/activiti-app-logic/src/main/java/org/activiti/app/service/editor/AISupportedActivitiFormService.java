/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.app.service.editor;

import org.activiti.app.service.exception.InternalServerErrorException;
import org.activiti.form.model.FormDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;


@Service
public class AISupportedActivitiFormService {

    private static final Logger logger = LoggerFactory.getLogger(AISupportedActivitiFormService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Inject
    private Environment environment;

    public FormDefinition generateFormFromPrompt(String prompt) {
        try {
            String llmConnectorUrl = environment.getProperty("application.llm-connector-url");
            String url = UriComponentsBuilder.fromHttpUrl(llmConnectorUrl)
                    .queryParam("prompt", prompt)
                    .toUriString();
            logger.info("calling LLM connector with url {}", url);
            return restTemplate.getForObject(url, FormDefinition.class);
        } catch (Exception e) {
            logger.error("Error calling LLM connector", e);
            throw new InternalServerErrorException("Error calling LLM connector", e);
        }
    }


}
