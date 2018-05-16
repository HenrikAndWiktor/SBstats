package se.wiktoreriksson.minerx.stats.spring.controller;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.wiktoreriksson.minerx.stats.spring.yaml.parse.Player;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.MessageFormat;

@RestController
public class MainController {
    @Autowired
    public HttpServletRequest httpsr;
    @RequestMapping(value = "/userapi",method = RequestMethod.GET,produces = "application/json")
    public String playerstats(@RequestParam("uuid") String uuid) {
        try {
            if (httpsr!=null) System.out.println(httpsr.getRemoteAddr());
            YamlReader y = new YamlReader(new FileReader(new File(MessageFormat.format("plugins/Essentials/userdata/{0}.yml", uuid))));
            Player p = y.read(Player.class);
            System.out.println("MainController.playerstats(String) query from "+httpsr.getRemoteAddr()+" try to get player ip "+p.ipAddress);
            return String.format("{\"uuid\":\"%s\",\"name\":\"%s\",\"xyz\":\"%s/%s/%s\",\"money\":\"%s\",\"yawpitch\":\"%s/%s\"}",uuid,p.lastAccountName,p.logoutlocation.get("x"),p.logoutlocation.get("y"),p.logoutlocation.get("z"),p.money,p.logoutlocation.get("yaw"),p.logoutlocation.get("pitch"));
        } catch (FileNotFoundException e) {
            return String.format("{\"uuid\":\"%s\",\"error\":\"Internal 404: Did'nt find userdata. Try to login to this minecraft server, logout and reload this page.\",\"stack\":\"%s\"}",uuid,e.getMessage());
        } catch (YamlException e) {
            return String.format("{\"uuid\":\"%s\",\"error\":\"%s\"}",uuid,e.getMessage());
        }
    }
}
