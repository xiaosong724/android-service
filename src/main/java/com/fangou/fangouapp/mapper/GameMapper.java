package com.fangou.fangouapp.mapper;

import com.fangou.fangouapp.vo.Pet;
import com.fangou.fangouapp.vo.PetExp;
import com.fangou.fangouapp.vo.Skill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GameMapper {
    /**
     * 查询所有的宠物信息
     * @return
     */
    @Select("SELECT * FROM pettab")
    List<Pet> queryPet();

    /**
     * 查询所有宠物的id
     * @return
     */
    @Select("SELECT pettab.id FROM pettab")
    List<Integer> queryid();

    /**
     * 查询宠物的信息
     * @param mname
     * @return
     */
    @Select("SELECT * FROM pettab WHERE mname=#{mname}")
    Pet quertPetmsg(String mname);
    /**
     * 查询所有技能
     * @return
     */
    @Select("SELECT * FROM skilltab")
    List<Skill> querySkill();

    /**
     * 判断当前是否升级
     * @param petname
     * @return
     */
    @Select("SELECT pettab.maxexp,pettab.exp FROM pettab WHERE petname=#{petname}")
    PetExp queryExp(String petname);

    @Update("UPDATE pettab SET pknum = 6,sign=0 WHERE id = #{id}")
    void updateEveryDay(int id);

    /**
     * pk后添加金币经验,胜利失败个不同
     * @param petname
     * @param addexp
     * @param addrmb
     */
    @Update("UPDATE pettab SET exp = exp+#{addexp} ,rmb=rmb+#{addrmb},pknum=pknum-1 WHERE petname = #{petname}")
    void addRmbAndExp(@Param("petname")String petname, @Param("addexp")int addexp, @Param("addrmb")int addrmb);


    /**
     * 升级后加成
     * @param petname
     * @param addexp
     */
    @Update("UPDATE pettab SET maxexp=maxexp+10,exp =#{addexp} ,herolevel=herolevel+1,wit=wit+10,hp=hp+50,akt=akt+10,con=con+10 WHERE petname = #{petname}")
    void addHerolevel(@Param("petname")String petname,@Param("addexp")int addexp);

}
