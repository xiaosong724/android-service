package com.fangou.fangouapp.service;

import com.fangou.fangouapp.mapper.GameMapper;
import com.fangou.fangouapp.vo.Pet;
import com.fangou.fangouapp.vo.PetExp;
import com.fangou.fangouapp.vo.Pkmsg;
import com.fangou.fangouapp.vo.Skill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceIml implements GameService {

    @Resource
    GameMapper gameMapper;

    @Override
    public List<Pkmsg> gamgPk(String mname) {
        //查询所有的宠物信息
        List<Pet> pets = gameMapper.queryPet();
        //查询所有的技能信息
        List<Skill> skills = gameMapper.querySkill();
        //我的宠物信息
        Pet petme = null;
        //我的技能信息主动
        List<Skill> skillssZdme = new ArrayList<>();
        //我的技能信息被动
        List<Skill> skillsBdme = new ArrayList<>();
        //他的技能信息主动
        List<Skill> skillssZdto = new ArrayList<>();
        //他的技能信息被动
        List<Skill> skillsBdto = new ArrayList<>();
        //他的宠物信息
        Pet petto = null;

        //1-100的随机数
        int rNumbei = (int) (1 + Math.random() * 100);
        System.out.println("本次的随机数是:" + rNumbei);
        //获取两个Pk的对象
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getMname().equals(mname)) {
                petme = pets.get(i);
            }else {
                petto = pets.get(i);
            }

        }
        //获取两方的技能
        for (int i = 0; i < skills.size(); i++) {
            //将我的主动技能放入List;
            if (petme.getId() == skills.get(i).getPetid() && (skills.get(i).getSkillproperty()).equals("主动")| (skills.get(i).getSkillproperty()).equals("必杀")) {
                skillssZdme.add(skills.get(i));
            }

            //将我的被动技能放入List
            if (petme.getId() == skills.get(i).getPetid() && (skills.get(i).getSkillproperty()).equals("被动")) {
                skillsBdme.add(skills.get(i));
            }
            //将他的主动技能放入List;
            if (petto.getId() == skills.get(i).getPetid() && (skills.get(i).getSkillproperty()).equals("主动")|(skills.get(i).getSkillproperty()).equals("必杀")) {
                skillssZdto.add(skills.get(i));
            }
            //将他的被动技能放入List;
            if (petto.getId() == skills.get(i).getPetid() && (skills.get(i).getSkillproperty()).equals("被动")) {
                skillsBdto.add(skills.get(i));
            }


        }
//        System.out.println("我的宠物信息:" + petme);
//        System.out.println("他的宠物信息:" + petto);
//        System.out.println("我的主动技能信息:" + skillssZdme);
//        System.out.println("我的被动技能信息:" + skillsBdme);
//        System.out.println("他的主动技能信息:" + skillssZdto);
//        System.out.println("他的被动技能信息:" + skillsBdto);
        List<Pkmsg> pk =null;
        if (rNumbei % 2 == 0) {
            System.out.println(petme.getPetname()+"先攻击");
            pk= pk(petme, petto, skillssZdme, skillsBdme, skillssZdto, skillsBdto);

        } else {
            System.out.println(petto.getPetname()+"先攻击111");
            pk = pk(petto, petme, skillssZdto, skillsBdto, skillssZdme, skillsBdme);
        }
        for (Pkmsg p:pk) {
            System.out.println(p.getHpreduceme()+"=============="+p.getHpreduceto());
            String boom=(p.getCsc()==2)?"暴击":"";
            System.out.println("本轮攻击方:"+p.getWhoakt()+",剩余血量:"+p.getHpreduceme()+",发动了:"+p.getSkillname()+","+boom+"造成伤害:"+p.getHarm());
            String fangshou=(p.getPassivity().equals("")?"没有触发被动":"触犯了被动:"+p.getPassivity()+"恢复了:"+p.getHpadd()+"HP");
            System.out.println("防御防御方:"+fangshou+",受到伤害"+p.getHarm()+",剩余血量:"+p.getHpreduceto());
            System.out.println((p.getWin().equals("")?"":p.getWin()));
            //加经验和金币,减少PK次数
            if(!p.getWin().equals("")&&petme.getPknum()>0){
                if(p.getWhoakt().equals(petme.getPetname())){
                    gameMapper.addRmbAndExp(petme.getPetname(),20,20);
                    //查询pk后是否升级
                    PetExp petExp = gameMapper.queryExp(petme.getPetname());
                    if(petExp.getExp()>=petExp.getMaxexp()){
                        System.out.println(petme.getPetname()+"升级了");
                        gameMapper.addHerolevel(petme.getPetname(),(petExp.getExp()-petExp.getMaxexp()));
                    }

                }else {
                    if(petme.getPknum()>0){
                        gameMapper.addRmbAndExp(petme.getPetname(),15,15);
                        //查询pk后是否升级
                        PetExp petExp = gameMapper.queryExp(petme.getPetname());
                        if(petExp.getExp()>=petExp.getMaxexp()){
                            System.out.println(petme.getPetname()+"升级了");
                            gameMapper.addHerolevel(petme.getPetname(),(petExp.getExp()-petExp.getMaxexp()));
                        }
                    }

                }
            }
        }
        return pk;
    }

    @Override
    public Pet quertPetmsg(String mname) {
        Pet pet = gameMapper.quertPetmsg(mname);
        return pet;
    }

    /**
     *  * private String whoakt;//谁发动攻击
     *      * private String skillname;//发动技能,攻击的名称
     *      * //这次攻击的伤害,多种情况,技能加成,暴击加成,必杀技加成,或技能暴击双叠加,或必杀技暴击双叠加
     *      * private int harm;
     *      * private int hpreduceme;//当前剩余血量
     *      * private int hpreduceto;//对方当前剩余血量
     *      * private String csc;//是否暴击或必杀技,或必杀加暴击,触发值为"暴击....",反之为空;
     *      * private String passivity;//对方是否触发了被动,触发值为被动名.反之为空;
     *      * private int hpadd;//触发被动所加血量,防御力乘于技能百分比;
     *      * private String win;//谁获得了胜利,血量小于0;
     * @param petme
     * @param petto
     * @param skillssZdme
     * @param skillsBdme
     * @param skillssZdto
     * @param skillsBdto
     */
    public List<Pkmsg> pk(Pet petme, Pet petto,List<Skill> skillssZdme,
                   List<Skill> skillsBdme,List<Skill> skillssZdto,List<Skill> skillsBdto) {
        //创建一个PK过程
        List<Pkmsg> pklist= new ArrayList<>();
        //攻击方的血量
        int hpme = petme.getHp();
        //防守方的血量
        int hpto = petto.getHp();
        boolean stop=true;
        while (stop){
            Pkmsg atktime = atktime(skillssZdme, skillsBdto, petme, petto, hpme, hpto);
            hpme=atktime.getHpreduceme();
            hpto=atktime.getHpreduceto();
            pklist.add(atktime);
            if (!atktime.getWin().equals("")){
                stop=false;
                break;
            }

            Pkmsg atktime1 = atktime(skillssZdto, skillsBdme, petto, petme, hpto, hpme);
            hpme=atktime1.getHpreduceto();
            hpto=atktime1.getHpreduceme();
            pklist.add(atktime1);
            if (!atktime1.getWin().equals("")){
                stop=false;
                break;
            }
        }
        return pklist;
    }

    /**
     *
     * @param skillsZdWho 这次攻击方的技能
     * @param skillsBdWho 这次防御方的技能
     * @param zd 获取攻击方的暴击几率
     *  @param bd 获取防御方的防御力
     * @param hpme 攻击方血量
     * @param hpto 防守方血量
     */
    public Pkmsg atktime(List<Skill> skillsZdWho,List<Skill> skillsBdWho,Pet zd,Pet bd,int hpme,int hpto){
        //攻击方发动技能随机数
        Pkmsg pkmsg=null;
        int rNumbeiZdme =(int) (1 + Math.random() * 100);
       // System.out.println("本次我的主动攻击随机数:"+rNumbeiZdme);
        //是否暴击随机数
        int rNumbeiBoomme = (int) (1 + Math.random() * 100);
      //  System.out.println("本次我的暴击随机数:"+rNumbeiBoomme);
        //对方是否发动被动技能随机数
        int rNumbeiBdto = (int) (1 + Math.random() * 100);
       // System.out.println("本次对方被动随机数:"+rNumbeiBdto);
        //攻击方攻击力
        int atkme=zd.getAkt();
        //防御方的防御力
        int conto=bd.getCon();
        //本轮发动攻击技能名称
        String jnZdgjme="普通攻击";
        //当前发动技能的伤害倍数;
        double beijiZdme=1.00;
        //本轮对方发动被动技能名称
        String jnBdjnto="";
        //被动倍数
        double beishuBdto=0;
        //本轮攻击是否暴击
        int jnZdbaojime=1;
        //每次循环,判断我发动的主动技能是否更换
        int skZdme=100;

        for (Skill listzd:skillsZdWho) {
            if(rNumbeiZdme<=listzd.getProbability()){
                if(skZdme>rNumbeiZdme){
                    skZdme=listzd.getProbability();
                    jnZdgjme=listzd.getSkillname();
                    beijiZdme=listzd.getHarm();
                    //System.out.println("本次循环我的主动技能名称:"+jnZdgjme+"暴击倍数:"+beijiZdme);
                }
            }
        }
        //每次循环,判断他发动的被动技能是否更换
        int skBdto=100;
        for (Skill listbd:skillsBdWho) {
            if(rNumbeiBdto<=listbd.getProbability()){
                if(skBdto>rNumbeiBdto){
                    skBdto=listbd.getProbability();
                    jnBdjnto=listbd.getSkillname();
                    beishuBdto=listbd.getHarm();
                   // System.out.println("本次循环他的被动技能名称:"+jnBdjnto+",恢复倍数:"+beishuBdto);
                }
            }
        }
        if(zd.getcsc()>=rNumbeiBoomme){
            jnZdbaojime=2;
            atkme= (int) (atkme*jnZdbaojime*beijiZdme)-(conto/3);
            System.out.println("暴击后伤害:"+atkme);
            //攻击后的血量
            hpto=hpto-atkme;
        }else {
            atkme= (int) (atkme*beijiZdme)-(conto/3);
            hpto=hpto-atkme;
        }
        //被动所加血量
        int addhp=(int)(beishuBdto*conto);
        hpto+=addhp;
        //谁获得了胜利
        String win="";
        if(hpto<0){
            win=zd.getPetname()+"获胜";
            pkmsg=new Pkmsg(zd.getPetname(),jnZdgjme,atkme,hpme,hpto,jnZdbaojime,jnBdjnto,addhp,win);
            return pkmsg;
        }
        pkmsg=new Pkmsg(zd.getPetname(),jnZdgjme,atkme,hpme,hpto,jnZdbaojime,jnBdjnto,addhp,win);
        return pkmsg;
    }
}
