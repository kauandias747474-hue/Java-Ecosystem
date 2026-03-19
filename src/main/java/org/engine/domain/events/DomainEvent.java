package com.evento;

import java.io.Serializable;


public record DomainEvent(String trackingCode, String payload) implements Serializable { }
