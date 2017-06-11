package me.gacl.dao;

import me.gacl.domain.Client;

public interface ClientMapper {
    int insert(Client record);

    int insertSelective(Client record);
}