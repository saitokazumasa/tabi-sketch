package com.tabisketch.service;

public interface IIsMatchPasswordService {
    boolean execute(final String mailAddress, final String password);
}
