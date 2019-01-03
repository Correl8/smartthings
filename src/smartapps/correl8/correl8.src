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
        input "accelerations", "capability.accelerationSensor", title: "Accelerations", required: false, multiple: true
        input "airqualitysensors", "capability.airQualitySensor", title: "Air quality sensors", required: false, multiple: true
        input "alarms", "capability.alarm", title: "Alarms", required: false, multiple: true
        input "batteries", "capability.battery", title: "Batteries", required:false, multiple: true
        input "buttons", "capability.button", title: "Buttons", required: false, multiple: true
        input "co2measurements", "capability.carbonDioxideMeasurement", title: "CO2 measurements", required:false, multiple: true
        input "codetectors", "capability.carbonMonoxideDetector", title: "CO detectors", required:false, multiple: true
        input "comeasurements", "capability.carbonMonoxideMeasurement", title: "CO measurements", required:false, multiple: true
        input "colorcontrols", "capability.colorControl", title: "Color controls", required:false, multiple: true
        input "colortemperatures", "capability.colorTemperature", title: "Color temperatures", required:false, multiple: true
        input "contacts", "capability.contactSensor", title: "Doors/windows", required: false, multiple: true
        input "doorcontrols", "capability.doorControl", title: "Door controls", required:false, multiple: true
        input "energies", "capability.energyMeter", title: "Energy Meters", required:false, multiple: true
        input "illuminances", "capability.illuminanceMeasurement", title: "Illuminances", required: false, multiple: true
        input "infrareds", "capability.infraredLevel", title: "Infrared levels", required: false, multiple: true
        input "locks", "capability.lock", title: "Locks", required: false, multiple: true
        input "motions", "capability.motionSensor", title: "Motions", required: false, multiple: true
        input "powermeters", "capability.powerMeter", title: "Power Meters", required:false, multiple: true
        input "powersources", "capability.powerSource", title: "Power Sources", required:false, multiple: true
        input "presences", "capability.presenceSensor", title: "Presence", required: false, multiple: true
        input "humidities", "capability.relativeHumidityMeasurement", title: "Humidities", required: false, multiple: true
        input "signalstrengths", "capability.signalStrength", title: "Signal strengths", required: false, multiple: true
        input "smokedetectors", "capability.smokeDetector", title: "Smoke detectors", required:false, multiple: true
        input "soundSensors", "capability.soundSensor", title: "Sound sensors", required: false, multiple: true
        input "switchlevels", "capability.switchLevel", title: "Switch levels", required: false, multiple: true
        input "switches", "capability.switch", title: "Switches", required: false, multiple: true
        input "tamperalerts", "capability.tamperAlert", title: "Tamper alerts", required:false, multiple: true
        input "temperatures", "capability.temperatureMeasurement", title: "Temperatures", required:false, multiple: true
        input "thermostatcoolingsetpoints", "capability.thermostatCoolingSetpoint", title: "Thermostat cooling setpoints", required:false, multiple: true
        input "thermostatfanmodes", "capability.thermostatFanMode", title: "Thermostat fan modes", required:false, multiple: true
        input "thermostatheatingsetpoints", "capability.thermostatHeatingSetpoint", title: "Thermostat heating setpoints", required:false, multiple: true
        input "thermostatmodes", "capability.thermostatMode", title: "Thermostat modes", required:false, multiple: true
        input "thermostatoperatingstates", "capability.thermostatOperatingState", title: "Thermostat operating states", required:false, multiple: true
        input "thermostatsetpoints", "capability.thermostatSetpoint", title: "Thermostat setpoints", required:false, multiple: true
        input "ultravioletindexes", "capability.ultravioletIndex", title: "Ultraviolet indexes", required: false, multiple: true
        input "valves", "capability.valve", title: "Valves", required: false, multiple: true
        input "voltages", "capability.voltageMeasurement", title: "Voltages", required:false, multiple: true
        input "waterSensors", "capability.waterSensor", title: "Water sensors", required: false, multiple: true
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
    accelerations.each{ dataTypes.put("${it}.acceleration", "boolean") }
    airqualitysensors.each{ dataTypes.put("${it}.level", "integer") }
    alarms.each{ dataTypes.put("${it}.alarm", "keyword") }
    batteries.each{ dataTypes.put("${it}.battery", "integer") }
    buttons.each{ dataTypes.put("${it}.button", "string") }
    co2measurements.each{ dataTypes.put("${it}.level", "integer") }
    codetectors.each{ dataTypes.put("${it}.detected", "boolean") }
    comeasurements.each{ dataTypes.put("${it}.level", "float") }
    colorcontrols.each{ dataTypes.put("${it}.hue", "integer") }
    colorcontrols.each{ dataTypes.put("${it}.saturation", "integer") }
    colortempereatures.each{ dataTypes.put("${it}.colorTemperature", "integer") }
    contacts.each{ dataTypes.put("${it}.open", "boolean") }
    doorcontrols.each{ dataTypes.put("${it}.door", "string") }
    energies.each{ dataTypes.put("${it}.energy", "float") }
    illuminances.each{ dataTypes.put("${it}.level", "float") }
    infrareds.each{ dataTypes.put("${it}.level", "float") }
    locks.each{ dataTypes.put("${it}.locked", "boolean") }
    motions.each{ dataTypes.put("${it}.motion", "boolean") }
    powermeters.each{ dataTypes.put("${it}.power", "float") }
    powersources.each{ dataTypes.put("${it}.source", "string") }
    presences.each{ dataTypes.put("${it}.presence", "boolean") }
    humidities.each{ dataTypes.put("${it}.humidity", "float") }
    signalstrengths.each{ dataTypes.put("${it}.lqi", "integer") }
    signalstrengths.each{ dataTypes.put("${it}.rssi", "float") }
    smokedetectors.each{ dataTypes.put("${it}.detected", "boolean") }
    soundsensors.each{ dataTypes.put("${it}.detected", "boolean") }
    switchlevels.each{ dataTypes.put("${it}.level", "integer") }
    switches.each{ dataTypes.put("${it}.switch", "boolean") }
    tamperalerts.each{ dataTypes.put("${it}.detected", "boolean") }
    temperatures.each{ dataTypes.put("${it}.temperature", "float") }
    thermostatcoolingsetpoints.each{ dataTypes.put("${it}.setpoint", "float") }
    thermostatfanmodes.each{ dataTypes.put("${it}.setpoint", "string") }
    thermostatheatingsetpoints.each{ dataTypes.put("${it}.setpoint", "float") }
    thermostatmodes.each{ dataTypes.put("${it}.mode", "string") }
    thermostatoperatingstates.each{ dataTypes.put("${it}.mode", "string") }
    thermostatsetpoints.each{ dataTypes.put("${it}.setpoint", "float") }
    ultravioletindexes.each{ dataTypes.put("${it}.level", "float") }
    valves.each{ dataTypes.put("${it}.open", "boolean") }
    voltages.each{ dataTypes.put("${it}.voltage", "float") }
    watersensors.each{ dataTypes.put("${it}.detected", "boolean") }

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

    subscribe(accelerations, "acceleration", handleAccelerationEvent)
    subscribe(airqualitysensors, "airQuality", handleLevel)
    subscribe(alarms, "alarm", handleString)
    subscribe(batteries, "battery", handleFloat)
    subscribe(buttons, "button", handleString)
    subscribe(co2measurements, "carbonDioxide", handleLevel)
    subscribe(codetectors, "carbonMonoxide", handleDetected)
    subscribe(comeasurements, "carbonMonoxideLevel", handleLevel)
    subscribe(colorcontrols, "hue", handleInteger)
    subscribe(colorcontrols, "saturation", handleInteger)
    subscribe(colortemperatures, "colorTemperature", handleInteger)
    subscribe(contacts, "contact", handleContactEvent)
    subscribe(doorcontrols, "door", handleString)
    subscribe(energies, "energy", handleFloat)
    subscribe(illuminances, "illuminance", handleLevel)
    subscribe(infrareds, "infraredLevel", handleLevel)
    subscribe(locks, "lock", handleLockEvent)
    subscribe(motions, "motion", handleMotionEvent)
    subscribe(powermeters, "power", handleFloat)
    subscribe(powersources, "power", handleString)
    subscribe(presence, "presence", handlePresenceEvent)
    subscribe(humidities, "humidity", handleFloat)
    subscribe(signalstrengths, "lqi", handleInteger)
    subscribe(signalstrengths, "rssi", handleFloat)
    subscribe(humidities, "humidity", handleFloat)
    subscribe(smokedetectors, "smoke", handleDetected)
    subscribe(soundsensors, "sound", handleDetected)
    subscribe(switches, "switch", handleSwitchEvent)
    subscribe(tamperalerts, "tamper", handleDetected)
    subscribe(temperatures, "temperature", handleFloat)
    subscribe(thermostatcoolingsetpoints, "coolingSetpoint", handleFloat)
    subscribe(thermostatfanmodes, "thermostatFanMode", handleString)
    subscribe(thermostatheatingsetpoints, "heatingSetpoint", handleFloat)
    subscribe(thermostatmodes, "thermostatMode", handleString)
    subscribe(thermostatoperatingstates, "thermostatOperatingState", handleString)
    subscribe(thermostatsetpoints, "thermostatSetpoint", handleFloat)
    subscribe(ultravioletindexes, "ultravioletIndex", handleLevel)
    subscribe(valves, "valve", handleContactEvent)
    subscribe(voltages, "voltage", handleFloat)
    subscribe(waterSensors, "water", handleWaterEvent)
}
 
def handleAccelerationEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: evt.name,
        value: evt.value == "active" ? "true" : "false"
    ]
    sendValue(e)
}
 
def handleButtonEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: "pushed",
        value: evt.value == "held" ? '"long"' : '"short"'
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
 
def handleDetected(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: "detected",
        value: evt.value == "detected" ? "true" : "false"
    ]
    sendValue(e)
}

def handleFloat(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: evt.name,
        value: Float.parseFloat(evt.value)
    ]
    sendValue(e)
}

def handleInteger(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: evt.name,
        value: Integer.parseInt(evt.value)
    ]
    sendValue(e)
}

def handleLevel(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: "level",
        value: evt.value
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
 
def handleString(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: evt.name,
        value: evt.value.trim()
    ]
    sendValue(e)
}

def handleSwitchEvent(evt) {
    def e = [
        sensor: evt.displayName.trim(),
        name: evt.name,
        value: evt.value == "on" ? "true" : "false"
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
