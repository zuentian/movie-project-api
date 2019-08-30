package com.zuer.movielvdoubanservice.mapper;

import com.zuer.movielvdoubanservice.entity.Client;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
public interface ClientMapper extends Mapper<Client> {

    List<String> selectAllowedClient(String serviceId);

    List<Client> selectAuthorityServiceInfo(int clientId);
}
