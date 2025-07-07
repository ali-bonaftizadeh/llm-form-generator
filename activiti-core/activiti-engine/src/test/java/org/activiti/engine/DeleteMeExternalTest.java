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
package org.activiti.engine;

import org.activiti.bpmn.converter.DeleteMe;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteMeExternalTest {
    private final DeleteMe deleteMe = new DeleteMe();

    @Test
    public void testCoveredUTExternally() {
        assertThat(deleteMe.coveredUTExternally()).isEqualTo("This line is covered by unit tests from an external module");
    }
}
