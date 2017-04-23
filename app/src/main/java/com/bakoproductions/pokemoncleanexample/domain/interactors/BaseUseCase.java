/*
 * Copyright 2017 Michael Bakogiannis, Open Source Project
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

package com.bakoproductions.pokemoncleanexample.domain.interactors;

import com.bakoproductions.pokemoncleanexample.domain.executors.BusProvider;
import com.squareup.otto.Bus;

/**
 * Created by Michael on 15/4/2017.
 *
 * This class is abstract because it provides the template of any use case we want to write
 * This can become really helpful when we need to add a functionality to perform authorization
 * requests between the calls, or to prevent the calls from executing when there is a
 * server downtime. In any ways this is it's basic template
 */
public abstract class BaseUseCase {
    private final Bus uiBus;
    private Object useCaseSubscriber;

    public BaseUseCase() {
        uiBus = BusProvider.getUIBusInstance();
    }

    protected abstract Object setSubscriber();
    protected abstract void onExecute();

    public void execute() {
        // We need the child to provide it's own subscribers
        useCaseSubscriber = setSubscriber();

        // Just think what you can do if you provide some functionality here that prevents some
        // use cases from executing when you know that the server is under heavy load
        // We jsut simply want to execute the use case
        onExecute();
    }

    // Posting data back to the presenter
    protected void post(Object event) {
        uiBus.post(event);
    }

    // Registering subscribers
    protected void registerUseCaseSubscriber() {
        try {
            BusProvider.getRestBusInstance().register(useCaseSubscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Unregistering subscribers
    protected void unregisterUseCaseSubscriber() {
        try {
            BusProvider.getRestBusInstance().unregister(useCaseSubscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
