package com.fangou.fangouapp.service;

import com.fangou.fangouapp.vo.Pet;
import com.fangou.fangouapp.vo.Pkmsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    List<Pkmsg> gamgPk(String mname);

    Pet quertPetmsg(String mname);

}
