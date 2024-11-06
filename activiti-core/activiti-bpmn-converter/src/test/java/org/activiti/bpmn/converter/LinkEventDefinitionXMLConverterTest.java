/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.bpmn.converter;

import org.activiti.bpmn.model.Event;
import org.activiti.bpmn.model.LinkEventDefinition;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class LinkEventDefinitionXMLConverterTest {
    @Mock
    private XMLStreamWriter xtw;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_writeLinkDefinition_whenEventDefinitionHasTarget() throws Exception {
        // Arrange
        Event parentEvent = mock(Event.class);
        LinkEventDefinition eventDefinition = new LinkEventDefinition();
        eventDefinition.setTarget("target");
        eventDefinition.setName("name");
        eventDefinition.setId("id");

        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter xtw = XMLOutputFactory.newInstance().createXMLStreamWriter(stringWriter);
        LinkEventDefinitionXMLConverter linkEventDefinitionXMLConverter = new LinkEventDefinitionXMLConverter();
        // Act
        linkEventDefinitionXMLConverter.writeLinkDefinition(parentEvent, eventDefinition, xtw);

       //Assert
        String generatedXml = stringWriter.toString();
        assertTrue(generatedXml.contains("<linkEventDefinition id=\"id\" name=\"name\">" +
                "<target>target</target></linkEventDefinition>"));
    }

    @Test
    public void should_writeLinkDefinition_whenEventDefinitionHasSources() throws Exception {
        // Arrange
        Event parentEvent = mock(Event.class);
        LinkEventDefinition eventDefinition = new LinkEventDefinition();

        List<String> sources= new ArrayList<>();
        sources.add("source1");
        sources.add("source2");

        eventDefinition.setName("name");
        eventDefinition.setId("id");
        eventDefinition.setSources(sources);

        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter xtw = XMLOutputFactory.newInstance().createXMLStreamWriter(stringWriter);
        LinkEventDefinitionXMLConverter linkEventDefinitionXMLConverter = new LinkEventDefinitionXMLConverter();
        // Act
        linkEventDefinitionXMLConverter.writeLinkDefinition(parentEvent, eventDefinition, xtw);

        //Assert
        String generatedXml = stringWriter.toString();
        assertTrue(generatedXml.contains("<linkEventDefinition id=\"id\" name=\"name\">" +
                "<source>source1</source><source>source2</source></linkEventDefinition>"));
    }
}
