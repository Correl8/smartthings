/**
 * Log SmartThings events to Correl8.me
 *
 * Copyright 2015 Samuel Rinnetmäki
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 * for the specific language governing permissions and limitations under the License.
 *
 */

definition(
    name: "Correl8",
    namespace: "Correl8",
    author: "Samuel Rinnetmäki",
    description: "Log to Correl8.me",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png"
) 
preferences {
    section("Log devices...") {
        input "temperatures", "capability.temperatureMeasurement", title: "Temperatures", required:false, multiple: true
        input "humidities", "capability.relativeHumidityMeasurement", title: "Humidities", required: false, multiple: true
        input "contacts", "capability.contactSensor", title: "Doors/windows", required: false, multiple: true
        input "locks", "capability.lock", title: "Locks", required: false, multiple: true
        input "accelerations", "capability.accelerationSensor", title: "Accelerations", required: false, multiple: true
        input "motions", "capability.motionSensor", title: "Motions", required: false, multiple: true
		input "presence", "capability.presenceSensor", title: "Presence", required: false, multiple: true
		input "beacons", "capability.beacon", title: "Beacons", required: false, multiple: true
/*  disabled temporarily
		input "buttons", "capability.button", title: "Buttons", required: false, multiple: true
*/
		input "switches", "capability.switch", title: "Switches", required: false, multiple: true
        input "waterSensors", "capability.waterSensor", title: "Water sensors", required: false, multiple: true
        input "batteries", "capability.battery", title: "Batteries", required:false, multiple: true
        input "powers", "capability.powerMeter", title: "Power Meters", required:false, multiple: true
        input "energies", "capability.energyMeter", title: "Energy Meters", required:false, multiple: true
    } 
    section ("Correl8 API key...") {
        input "apiKey", "text", title: "API key"
    }
}
 
def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}
 
def updated() {
    unsubscribe()
    initialize()
}

def initialize() {
	log.debug "Initializing smartthings: ${settings}"
    def dataTypes = [:]
    temperatures.each{ dataTypes.put("${it}.temperature", "float") }
    humidities.each{ dataTypes.put("${it}.humidity", "float") }
    contacts.each{ dataTypes.put("${it}.open", "boolean") }
    locks.each{ dataTypes.put("${it}.locked", "boolean") }
    accelerations.each{ dataTypes.put("${it}.acceleration", "boolean") }
    motions.each{ dataTypes.put("${it}.motion", "boolean") }
    presence.each{ dataTypes.put("${it}.presence", "boolean") }
    beacons.each{ dataTypes.put("${it}.presence", "boolean") }
/*  disabled temporarily
    buttons.each{ dataTypes.put("${it}.pushed", "string") }
*/
    switches.each{ dataTypes.put("${it}.switch", "boolean") }
    watersensors.each{ dataTypes.put("${it}.water", "boolean") }
    batteries.each{ dataTypes.put("${it}.battery", "float") }
    powers.each{ dataTypes.put("${it}.power", "float") }
    energies.each{ dataTypes.put("${it}.energy", "float") }
    log.debug "Initializing correl8 with: ${dataTypes}"

	def params = [
	    uri: "http://api.correl8.me",
    	path: "/smartthings/init/",
	    query: dataTypes
	]
	try {
    	httpGet(params) { resp ->
			log.debug "Initialized ${resp.status}"
	    }
	} catch (e) {
    	log.warn "Correl8.me initialization failed: ${e}"
	}

    subscribe(temperatures, "temperature", handleTemperatureEvent)
    subscribe(humidities, "humidity", handleHumidityEvent)
    subscribe(contacts, "contact", handleContactEvent)
    subscribe(locks, "lock", handleLockEvent)
    subscribe(accelerations, "acceleration", handleAccelerationEvent)
    subscribe(motions, "motion", handleMotionEvent)
    subscribe(presence, "presence", handlePresenceEvent)
    subscribe(beacons, "presence", handlePresenceEvent)
/*  disabled temporarily
    subscribe(buttons, "button", handleButtonEvent)
*/
	subscribe(switches, "switch", handleSwitchEvent)
    subscribe(waterSensors, "water", handleWaterEvent)
    subscribe(batteries, "battery", handleBatteryEvent)
    subscribe(powers, "power", handlePowerEvent)
    subscribe(energies, "energy", handleEnergyEvent)
}
 
def handleTemperatureEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: Float.parseFloat(evt.value)
    ]
    sendValue(e)
}
 
def handleWaterEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: "wet",
        value: evt.value == "wet" ? "true" : "false"
    ]
    sendValue(e)
}
 
def handleHumidityEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: Float.parseFloat(evt.value)
    ]
    sendValue(e)
}
 
def handleContactEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: "open",
        value: evt.value == "open" ? "true" : "false"
    ]
    sendValue(e)
}
 
def handleLockEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: "locked",
        value: evt.value == "locked" ? "true" : "false"
    ]
    sendValue(e)
}
 
def handleAccelerationEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: evt.value == "active" ? "true" : "false"
    ]
    sendValue(e)
}
 
def handleMotionEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: evt.value == "active" ? "true" : "false"
    ]
    sendValue(e)
}
 
def handlePresenceEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: evt.value == "present" ? "true" : "false"
    ]
    sendValue(e)
}
 
/*  disabled temporarily
def handleButtonEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: "pushed",
        value: evt.value == "held" ? '"long"' : '"short"'
    ]
    sendValue(e)
}
*/

def handleSwitchEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: evt.value == "on" ? "true" : "false"
    ]
    sendValue(e)
}

def handleBatteryEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: Float.parseFloat(evt.value)
    ]
    sendValue(e)
}

def handlePowerEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: Float.parseFloat(evt.value)
    ]
    sendValue(e)
}

def handleEnergyEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
	    name: evt.name,
        value: Float.parseFloat(evt.value)
    ]
    sendValue(e)
}
 
private sendValue(event) {
	def sensor = event.sensor
    def name = event.name
	def value = event.value
       
	log.debug "Logging ${sensor} to Correl8: ${name} = ${value}"
    // hand crafted JSON
    def msgBody = """{"${sensor}":{"${name}": ${value}}}"""

    log.debug "${msgBody}"
    
    def params = [
        // headers: ["Content-Type": "application/json"],
    	uri: "https://api.correl8.me",
    	path: "/smartthings",
        body: msgBody
	]

	try {
    	httpPostJson(params) { resp ->
			log.debug "Logged ${resp.status}"
	    }
	} catch (e) {
    	log.warn "Logging to Correl8.me failed: ${e}"
	}
}
