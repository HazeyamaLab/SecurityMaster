package lab.haze.SecurityMaster.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lab.haze.SecurityMaster.Model.BadgeTimeline;
import lab.haze.SecurityMaster.Model.User;
import lab.haze.SecurityMaster.Model.UserBadge;
import lab.haze.SecurityMaster.Model.UserStatus;
import lab.haze.SecurityMaster.Repository.BadgeTimelineRepository;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Repository.UserStatusRepository;
import lab.haze.SecurityMaster.Service.BadgeTimelineServiceImpl;
import lab.haze.SecurityMaster.Service.UserBadgeServiceImpl;
import lab.haze.SecurityMaster.Service.UserServiceImpl;

@Controller
public class LearnController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserBadgeServiceImpl userBadgeServiceImpl;

    @Autowired
    BadgeTimelineServiceImpl BadgeTimelineServiceImpl;

    @Autowired
    BadgeTimelineRepository badgeTimelineRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/status")
        public String status(Model model,@AuthenticationPrincipal User user){
            List<UserStatus> list = new ArrayList<>();
            boolean isStatusExists = userStatusRepository.existsByUserId(user.getId());
            int count = 0; 
            if(isStatusExists){
                list = userStatusRepository.findByUserId(user.getId());
                boolean flag1 = false; 
                boolean flag2 = false;
                boolean flag3 = false;
                boolean flag4 = false;
                boolean flag5 = false;
                boolean flag6 = false;
                boolean flag7 = false;
                for(UserStatus userStatus : list){
                    if(userStatus.getLearnId() == 1 && !flag1){
                        count ++;
                        flag1 = true;
                    }else if(userStatus.getLearnId() == 2 && !flag2){
                        count ++;
                        flag2 = true;
                    }
                    else if(userStatus.getLearnId() == 3 && !flag3){
                        count ++;
                        flag3 = true;
                    }
                    else if(userStatus.getLearnId() == 4 && !flag4){
                        count ++;
                        flag4 = true;
                    }
                    else if(userStatus.getLearnId() == 5 && !flag5){
                        count ++;
                        flag5 = true;
                    }
                    else if(userStatus.getLearnId() == 6 && !flag6){
                        count ++;
                        flag6 = true;
                    }
                    else if(userStatus.getLearnId() == 7 && !flag7){
                        count ++;
                        flag7 = true;
                    }
                }
                
                model.addAttribute("list", list);

            }
            model.addAttribute("count",count);
            model.addAttribute("user", user);
            return "status";
    }



    @GetMapping("/learn/prologue")
    public String prologue(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("userName", user.getName());
        model.addAttribute("companyName", user.getCompanyName());
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
        BadgeTimeline badgeTimeline = new BadgeTimeline();
        if(!userBadge.isBadge1()){
            userBadge.setBadge1(true);
            badgeTimeline.setBadgeId(1);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
        }
        return "learn/prologue";
    }
    @GetMapping("/learn")
    public String learn(){
        return "learn";
    }

    //----------------------------------Injection---------------------------------------------------------------

    @GetMapping("/learn/1injection/intro")
    public String learn1(@AuthenticationPrincipal User user){
        httpSession.setAttribute("preWorth", user.getCompanyWorth());
        return "learn/1injection/intro";
    }

    @GetMapping("/learn/1injection/lecture")
    public String lecture1(){
        return "learn/1injection/lecture";
    
    }

    @GetMapping("/learn/1injection/test1")
    public String injtest1(){
        return "learn/1injection/test1";
    }

    @PostMapping("/learn/1injection/test1")
    public String postTest1(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 4) {
            httpSession.setAttribute("colCount", "0");
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/1injection/inc1";
        } else {
            httpSession.setAttribute("colCount", "1");
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/1injection/col1";
        }
    }
    
    @GetMapping("/learn/1injection/inc1")
    public String injInc1(@AuthenticationPrincipal User user){
        //??????????????????????????????
        return "learn/1injection/inc1";
    }

    @GetMapping("/learn/1injection/col1")
    public String injCol1(@AuthenticationPrincipal User user) {
        //??????????????????????????????
        return "learn/1injection/col1";
    }
    
    @PostMapping("/learn/1injection/test2")
    public String postTest2(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 4) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/1injection/inc2";
        } else {
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            return "learn/1injection/col2";
        }
    }
    @GetMapping("/learn/1injection/test2")
    public String injtest2(){
        return "learn/1injection/test2";
    }

    @GetMapping("/learn/1injection/inc2")
    public String injInc2(@AuthenticationPrincipal User user){
        //??????????????????????????????
        return "learn/1injection/inc2";
    }

    @GetMapping("/learn/1injection/col2")
    public String injCol2(@AuthenticationPrincipal User user) {
        //??????????????????????????????
        return "learn/1injection/col2";
    }

    @GetMapping("/learn/1injection/fin")
    public String injfin(Model model,@AuthenticationPrincipal User user){
        Object c =  httpSession.getAttribute("colCount");
        String cs = c.toString();
        int count = Integer.parseInt(cs);
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
        BadgeTimeline badgeTimeline = new BadgeTimeline();
        if(!userBadge.isBadge2()){
            userBadge.setBadge2(true);
            badgeTimeline.setBadgeId(2);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        if(count == 2 && !userBadge.isBadge3()){
            userBadge.setBadge3(true);
            badgeTimeline.setBadgeId(3);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        
        UserStatus userStatus = new UserStatus();
        userStatus.setUserId(user.getId());
        userStatus.setLearnId(1);
        LocalDateTime now = LocalDateTime.now();
        now.plusHours(9);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        userStatus.setLtd(now.format(formatter));
        userStatus.setCorrect(count);
        userStatusRepository.save(userStatus);

        userBadgeServiceImpl.updateBadge(userBadge);
        model.addAttribute("preWorth", httpSession.getAttribute("preWorth"));
        model.addAttribute("worth", user.getCompanyWorth());
        return "learn/1injection/fin";
    }

    //-----------------------------------------------------------------------------------
    @GetMapping("/learn/2directory/hello")
    public String learn2hello(@AuthenticationPrincipal User user){
        httpSession.setAttribute("preWorth", user.getCompanyWorth());
        return "learn/2directory/hello";
    }
    @GetMapping("/learn/2directory/pretest1")
    public String dirpretest1(){
        return "learn/2directory/pretest1";
    }
    @GetMapping("/learn/2directory/pretest2")
    public String dirpretest2(){
        return "learn/2directory/pretest2";
    }
    @PostMapping("/learn/2directory/pretest1")
    public String dirPreTest1(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 2) {
            httpSession.setAttribute("colCount", "0");
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/preinc1";
        } else {
            httpSession.setAttribute("colCount", "1");
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/precol1";
        }
    }
    @PostMapping("/learn/2directory/pretest2")
    public String dirPreTest2(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 1) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/preinc2";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/precol2";
        }
    }
    @GetMapping("/learn/2directory/intro")
    public String learn2intro(@AuthenticationPrincipal User user){
        return "learn/2directory/intro";
    }
    @GetMapping("/learn/2directory/lecture")
    public String lecture2(){
        return "learn/2directory/lecture";
    }
    @GetMapping("/learn/2directory/test1")
    public String dirtest1(){
        return "learn/2directory/test1";
    }
    @GetMapping("/learn/2directory/test2")
    public String dirtest2(){
        return "learn/2directory/test2";
    }

    @PostMapping("/learn/2directory/test1")
    public String dirTest1(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 2) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/inc1";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/col1";
        }
    }
    @PostMapping("/learn/2directory/test2")
    public String dirTest2(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 3) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/inc2";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/2directory/col2";
        }
    }
    @GetMapping("/learn/2directory/fin")
    public String dirfin(Model model,@AuthenticationPrincipal User user){
        Object c =  httpSession.getAttribute("colCount");
        String cs = c.toString();
        int count = Integer.parseInt(cs);
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
        
        System.out.println(count);
        if(!userBadge.isBadge4()){
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            userBadge.setBadge4(true);
            badgeTimeline.setBadgeId(4);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
            
        }

        if(count == 4 && !userBadge.isBadge5()){
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            userBadge.setBadge5(true);
            badgeTimeline.setBadgeId(5);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
           
        }
        
        UserStatus userStatus = new UserStatus();
        userStatus.setUserId(user.getId());
        userStatus.setLearnId(2);
        LocalDateTime now = LocalDateTime.now();
        now.plusHours(9);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        userStatus.setLtd(now.format(formatter));
        userStatus.setCorrect(count);
        userStatusRepository.save(userStatus);

        userBadgeServiceImpl.updateBadge(userBadge);
        model.addAttribute("preWorth", httpSession.getAttribute("preWorth"));
        model.addAttribute("worth", user.getCompanyWorth());
        return "learn/2directory/fin";
    }
    //-----------------------------------------------------------------------------------
    @GetMapping("/learn/3session/hello")
    public String learn3hello(@AuthenticationPrincipal User user){
        httpSession.setAttribute("preWorth", user.getCompanyWorth());
        return "learn/3session/hello";
    }
    @GetMapping("/learn/3session/pretest1")
    public String dirpretest3(){
        return "learn/3session/pretest1";
    }
    @GetMapping("/learn/3session/pretest2")
    public String dirpretest32(){
        return "learn/3session/pretest2";
    }
    @PostMapping("/learn/3session/pretest1")
    public String dirPreTest33(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 2) {
            httpSession.setAttribute("colCount", "0");
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/preinc1";
        } else {
            httpSession.setAttribute("colCount", "1");
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/precol1";
        }
    }
    @PostMapping("/learn/3session/pretest2")
    public String dirPreTest22(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 3) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/preinc2";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/precol2";
        }
    }
    @GetMapping("/learn/3session/intro")
    public String learn3intro(@AuthenticationPrincipal User user){
        return "learn/3session/intro";
    }
    @GetMapping("/learn/3session/lecture")
    public String lecture3(){
        return "learn/3session/lecture";
    }
    @GetMapping("/learn/3session/test1")
    public String dirtest5(){
        return "learn/3session/test1";
    }
    @GetMapping("/learn/3session/test2")
    public String dirtest3(){
        return "learn/3session/test2";
    }

    @PostMapping("/learn/3session/test1")
    public String dirTest11(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 4) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/inc1";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/col1";
        }
    }
    @PostMapping("/learn/3session/test2")
    public String dirTest22(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 3) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/inc2";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/3session/col2";
        }
    }
    @GetMapping("/learn/3session/fin")
    public String dirfin3(Model model,@AuthenticationPrincipal User user){
        Object c =  httpSession.getAttribute("colCount");
        String cs = c.toString();
        int count = Integer.parseInt(cs);
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
        
        System.out.println(count);
        if(!userBadge.isBadge6()){
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            userBadge.setBadge6(true);
            badgeTimeline.setBadgeId(6);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
            
        }
        
        if(count == 4 && !userBadge.isBadge7()){
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            userBadge.setBadge7(true);
            badgeTimeline.setBadgeId(7);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        
        UserStatus userStatus = new UserStatus();
        userStatus.setUserId(user.getId());
        userStatus.setLearnId(3);
        LocalDateTime now = LocalDateTime.now();
        now.plusHours(9);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        userStatus.setLtd(now.format(formatter));
        userStatus.setCorrect(count);
        userStatusRepository.save(userStatus);

        userBadgeServiceImpl.updateBadge(userBadge);
        model.addAttribute("preWorth", httpSession.getAttribute("preWorth"));
        model.addAttribute("worth", user.getCompanyWorth());
        return "learn/3session/fin";
    }

    //--------------------------------------------------------------------------------------


    @GetMapping("/learn/4xss/hello")
    public String learn4hello(@AuthenticationPrincipal User user){
        httpSession.setAttribute("preWorth", user.getCompanyWorth());
        return "learn/4xss/hello";
    }
    @GetMapping("/learn/4xss/pretest1")
    public String dirpretest34(){
        return "learn/4xss/pretest1";
    }
    @GetMapping("/learn/4xss/pretest2")
    public String dirpretest35(){
        return "learn/4xss/pretest2";
    }
    @PostMapping("/learn/4xss/pretest1")
    public String dirPreTest36(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 2) {
            httpSession.setAttribute("colCount", "0");
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/preinc1";
        } else {
            httpSession.setAttribute("colCount", "1");
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/precol1";
        }
    }
    @PostMapping("/learn/4xss/pretest2")
    public String dirPreTest224(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 4) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/preinc2";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/precol2";
        }
    }
    @GetMapping("/learn/4xss/intro")
    public String learn4intro(@AuthenticationPrincipal User user){
        return "learn/4xss/intro";
    }
    @GetMapping("/learn/4xss/lecture")
    public String lecture4(){
        return "learn/4xss/lecture";
    }
    @GetMapping("/learn/4xss/test1")
    public String dirtest6(){
        return "learn/4xss/test1";
    }
    @GetMapping("/learn/4xss/test2")
    public String dirtest66(){
        return "learn/4xss/test2";
    }

    @PostMapping("/learn/4xss/test1")
    public String dirTest116(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 1) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/inc1";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/col1";
        }
    }
    @PostMapping("/learn/4xss/test2")
    public String dirTest224(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
        int ians = Integer.parseInt(ans);
        if (ians != 4) {
            double worth = user.getCompanyWorth();
            worth = worth * 0.9;
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/inc2";
        } else {
            Object c =  httpSession.getAttribute("colCount");
            String cs = c.toString();
            int count = Integer.parseInt(cs);
            count += 1;
            Integer countInteger = Integer.valueOf(count);
            String countString = countInteger.toString();
            httpSession.setAttribute("colCount", countString);
            double worth = user.getCompanyWorth();
            Random Random = new Random();
            double ratio = Random.nextDouble();
            worth = worth * (1.0 +(0.2 * ratio));
            System.out.println(worth);
            user.setCompanyWorth((int) worth);
            userServiceImpl.updateWorth(user);
            return "learn/4xss/col2";
        }
    }
    @GetMapping("/learn/4xss/fin")
    public String dirfin4(Model model,@AuthenticationPrincipal User user){
        Object c =  httpSession.getAttribute("colCount");
        String cs = c.toString();
        int count = Integer.parseInt(cs);
        UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
        
        System.out.println(count);
        if(!userBadge.isBadge8()){
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            userBadge.setBadge8(true);
            badgeTimeline.setBadgeId(8);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
            
        }
        
        if(count == 4 && !userBadge.isBadge9()){
            BadgeTimeline badgeTimeline = new BadgeTimeline();
            userBadge.setBadge9(true);
            badgeTimeline.setBadgeId(9);
            badgeTimeline.setUserId(user.getId());
            LocalDateTime now = LocalDateTime.now();
            now.plusHours(9);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            badgeTimeline.setLtd(now.format(formatter));
            badgeTimelineRepository.save(badgeTimeline);
            badgeTimelineRepository.flush();
        }
        
        UserStatus userStatus = new UserStatus();
        userStatus.setUserId(user.getId());
        userStatus.setLearnId(4);
        LocalDateTime now = LocalDateTime.now();
        now.plusHours(9);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        userStatus.setLtd(now.format(formatter));
        userStatus.setCorrect(count);
        userStatusRepository.save(userStatus);

        userBadgeServiceImpl.updateBadge(userBadge);
        model.addAttribute("preWorth", httpSession.getAttribute("preWorth"));
        model.addAttribute("worth", user.getCompanyWorth());
        return "learn/4xss/fin";
    }

     //--------------------------------------------------------------------------------------


     @GetMapping("/learn/5csrf/hello")
     public String learn5hello(@AuthenticationPrincipal User user){
         httpSession.setAttribute("preWorth", user.getCompanyWorth());
         return "learn/5csrf/hello";
     }
     @GetMapping("/learn/5csrf/pretest1")
     public String dirpretest345(){
         return "learn/5csrf/pretest1";
     }
     @GetMapping("/learn/5csrf/pretest2")
     public String dirpretest356(){
         return "learn/5csrf/pretest2";
     }
     @PostMapping("/learn/5csrf/pretest1")
     public String dirPreTest367(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 4) {
             httpSession.setAttribute("colCount", "0");
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/preinc1";
         } else {
             httpSession.setAttribute("colCount", "1");
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/precol1";
         }
     }
     @PostMapping("/learn/5csrf/pretest2")
     public String dirPreTest2245(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 3) {
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/preinc2";
         } else {
             Object c =  httpSession.getAttribute("colCount");
             String cs = c.toString();
             int count = Integer.parseInt(cs);
             count += 1;
             Integer countInteger = Integer.valueOf(count);
             String countString = countInteger.toString();
             httpSession.setAttribute("colCount", countString);
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/precol2";
         }
     }
     @GetMapping("/learn/5csrf/intro")
     public String learn5intro(@AuthenticationPrincipal User user){
         return "learn/5csrf/intro";
     }
     @GetMapping("/learn/5csrf/lecture")
     public String lecture45(){
         return "learn/5csrf/lecture";
     }
     @GetMapping("/learn/5csrf/test1")
     public String dirtest67(){
         return "learn/5csrf/test1";
     }
     @GetMapping("/learn/5csrf/test2")
     public String dirtest668(){
         return "learn/5csrf/test2";
     }
 
     @PostMapping("/learn/5csrf/test1")
     public String dirTest1167(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 2) {
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/inc1";
         } else {
             Object c =  httpSession.getAttribute("colCount");
             String cs = c.toString();
             int count = Integer.parseInt(cs);
             count += 1;
             Integer countInteger = Integer.valueOf(count);
             String countString = countInteger.toString();
             httpSession.setAttribute("colCount", countString);
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/col1";
         }
     }
     @PostMapping("/learn/5csrf/test2")
     public String dirTest2245(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 1) {
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/inc2";
         } else {
             Object c =  httpSession.getAttribute("colCount");
             String cs = c.toString();
             int count = Integer.parseInt(cs);
             count += 1;
             Integer countInteger = Integer.valueOf(count);
             String countString = countInteger.toString();
             httpSession.setAttribute("colCount", countString);
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/5csrf/col2";
         }
     }
     @GetMapping("/learn/5csrf/fin")
     public String dirfin45(Model model,@AuthenticationPrincipal User user){
         Object c =  httpSession.getAttribute("colCount");
         String cs = c.toString();
         int count = Integer.parseInt(cs);
         UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
         
         System.out.println(count);
         if(!userBadge.isBadge10()){
             BadgeTimeline badgeTimeline = new BadgeTimeline();
             userBadge.setBadge10(true);
             badgeTimeline.setBadgeId(10);
             badgeTimeline.setUserId(user.getId());
             LocalDateTime now = LocalDateTime.now();
             now.plusHours(9);
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
             badgeTimeline.setLtd(now.format(formatter));
             badgeTimelineRepository.save(badgeTimeline);
             badgeTimelineRepository.flush();
             
         }
         
         if(count == 4 && !userBadge.isBadge11()){
             BadgeTimeline badgeTimeline = new BadgeTimeline();
             userBadge.setBadge11(true);
             badgeTimeline.setBadgeId(11);
             badgeTimeline.setUserId(user.getId());
             LocalDateTime now = LocalDateTime.now();
             now.plusHours(9);
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
             badgeTimeline.setLtd(now.format(formatter));
             badgeTimelineRepository.save(badgeTimeline);
             badgeTimelineRepository.flush();
         }
         
         UserStatus userStatus = new UserStatus();
         userStatus.setUserId(user.getId());
         userStatus.setLearnId(5);
         LocalDateTime now = LocalDateTime.now();
         now.plusHours(9);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         userStatus.setLtd(now.format(formatter));
         userStatus.setCorrect(count);
         userStatusRepository.save(userStatus);
 
         userBadgeServiceImpl.updateBadge(userBadge);
         model.addAttribute("preWorth", httpSession.getAttribute("preWorth"));
         model.addAttribute("worth", user.getCompanyWorth());
         return "learn/5csrf/fin";
     }
     //--------------------------------------------------------------------------------------


     @GetMapping("/learn/6hedder/hello")
     public String learn5hello2(@AuthenticationPrincipal User user){
         httpSession.setAttribute("preWorth", user.getCompanyWorth());
         return "learn/6hedder/hello";
     }
     @GetMapping("/learn/6hedder/pretest1")
     public String dirpretest3452(){
         return "learn/6hedder/pretest1";
     }
     @GetMapping("/learn/6hedder/pretest2")
     public String dirpretest3562(){
         return "learn/6hedder/pretest2";
     }
     @PostMapping("/learn/6hedder/pretest1")
     public String dirPreTest3672(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 4) {
             httpSession.setAttribute("colCount", "0");
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/preinc1";
         } else {
             httpSession.setAttribute("colCount", "1");
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/precol1";
         }
     }
     @PostMapping("/learn/6hedder/pretest2")
     public String dirPreTest22425(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 1) {
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/preinc2";
         } else {
             Object c =  httpSession.getAttribute("colCount");
             String cs = c.toString();
             int count = Integer.parseInt(cs);
             count += 1;
             Integer countInteger = Integer.valueOf(count);
             String countString = countInteger.toString();
             httpSession.setAttribute("colCount", countString);
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/precol2";
         }
     }
     @GetMapping("/learn/6hedder/intro")
     public String learn52intro(@AuthenticationPrincipal User user){
         return "learn/6hedder/intro";
     }
     @GetMapping("/learn/6hedder/lecture")
     public String lecture425(){
         return "learn/6hedder/lecture";
     }
     @GetMapping("/learn/6hedder/test1")
     public String dirtest627(){
         return "learn/6hedder/test1";
     }
     @GetMapping("/learn/6hedder/test2")
     public String dirtest6628(){
         return "learn/6hedder/test2";
     }
 
     @PostMapping("/learn/6hedder/test1")
     public String dirTest11627(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 2) {
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/inc1";
         } else {
             Object c =  httpSession.getAttribute("colCount");
             String cs = c.toString();
             int count = Integer.parseInt(cs);
             count += 1;
             Integer countInteger = Integer.valueOf(count);
             String countString = countInteger.toString();
             httpSession.setAttribute("colCount", countString);
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/col1";
         }
     }
     @PostMapping("/learn/6hedder/test2")
     public String dirTest22245(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
         int ians = Integer.parseInt(ans);
         if (ians != 4) {
             double worth = user.getCompanyWorth();
             worth = worth * 0.9;
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/inc2";
         } else {
             Object c =  httpSession.getAttribute("colCount");
             String cs = c.toString();
             int count = Integer.parseInt(cs);
             count += 1;
             Integer countInteger = Integer.valueOf(count);
             String countString = countInteger.toString();
             httpSession.setAttribute("colCount", countString);
             double worth = user.getCompanyWorth();
             Random Random = new Random();
             double ratio = Random.nextDouble();
             worth = worth * (1.0 +(0.2 * ratio));
             System.out.println(worth);
             user.setCompanyWorth((int) worth);
             userServiceImpl.updateWorth(user);
             return "learn/6hedder/col2";
         }
     }
     @GetMapping("/learn/6hedder/fin")
     public String dirfin425(Model model,@AuthenticationPrincipal User user){
         Object c =  httpSession.getAttribute("colCount");
         String cs = c.toString();
         int count = Integer.parseInt(cs);
         UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
         
         System.out.println(count);
         if(!userBadge.isBadge12()){
             BadgeTimeline badgeTimeline = new BadgeTimeline();
             userBadge.setBadge12(true);
             badgeTimeline.setBadgeId(12);
             badgeTimeline.setUserId(user.getId());
             LocalDateTime now = LocalDateTime.now();
             now.plusHours(9);
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
             badgeTimeline.setLtd(now.format(formatter));
             badgeTimelineRepository.save(badgeTimeline);
             badgeTimelineRepository.flush();
             
         }
         
         if(count == 4 && !userBadge.isBadge13()){
             BadgeTimeline badgeTimeline = new BadgeTimeline();
             userBadge.setBadge13(true);
             badgeTimeline.setBadgeId(13);
             badgeTimeline.setUserId(user.getId());
             LocalDateTime now = LocalDateTime.now();
             now.plusHours(9);
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
             badgeTimeline.setLtd(now.format(formatter));
             badgeTimelineRepository.save(badgeTimeline);
             badgeTimelineRepository.flush();
         }
         
         UserStatus userStatus = new UserStatus();
         userStatus.setUserId(user.getId());
         userStatus.setLearnId(6);
         LocalDateTime now = LocalDateTime.now();
         now.plusHours(9);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         userStatus.setLtd(now.format(formatter));
         userStatus.setCorrect(count);
         userStatusRepository.save(userStatus);
 
         userBadgeServiceImpl.updateBadge(userBadge);
         model.addAttribute("preWorth", httpSession.getAttribute("preWorth"));
         model.addAttribute("worth", user.getCompanyWorth());
         return "learn/6hedder/fin";
     }
      //--------------------------------------------------------------------------------------


      @GetMapping("/learn/7jack/hello")
      public String learn75hello2(@AuthenticationPrincipal User user){
          httpSession.setAttribute("preWorth", user.getCompanyWorth());
          return "learn/7jack/hello";
      }
      @GetMapping("/learn/7jack/pretest1")
      public String dirpretest34572(){
          return "learn/7jack/pretest1";
      }
      @GetMapping("/learn/7jack/pretest2")
      public String dirpretest35672(){
          return "learn/7jack/pretest2";
      }
      @PostMapping("/learn/7jack/pretest1")
      public String dirPreTest36772(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
          int ians = Integer.parseInt(ans);
          if (ians != 4) {
              httpSession.setAttribute("colCount", "0");
              double worth = user.getCompanyWorth();
              worth = worth * 0.9;
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/preinc1";
          } else {
              httpSession.setAttribute("colCount", "1");
              double worth = user.getCompanyWorth();
              Random Random = new Random();
              double ratio = Random.nextDouble();
              worth = worth * (1.0 +(0.2 * ratio));
              System.out.println(worth);
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/precol1";
          }
      }
      @PostMapping("/learn/7jack/pretest2")
      public String dirPreTest272425(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
          int ians = Integer.parseInt(ans);
          if (ians != 3) {
              double worth = user.getCompanyWorth();
              worth = worth * 0.9;
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/preinc2";
          } else {
              Object c =  httpSession.getAttribute("colCount");
              String cs = c.toString();
              int count = Integer.parseInt(cs);
              count += 1;
              Integer countInteger = Integer.valueOf(count);
              String countString = countInteger.toString();
              httpSession.setAttribute("colCount", countString);
              double worth = user.getCompanyWorth();
              Random Random = new Random();
              double ratio = Random.nextDouble();
              worth = worth * (1.0 +(0.2 * ratio));
              System.out.println(worth);
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/precol2";
          }
      }
      @GetMapping("/learn/7jack/intro")
      public String learn527ntro(@AuthenticationPrincipal User user){
          return "learn/7jack/intro";
      }
      @GetMapping("/learn/7jack/lecture")
      public String lecture4275(){
          return "learn/7jack/lecture";
      }
      @GetMapping("/learn/7jack/test1")
      public String dirtest6277(){
          return "learn/7jack/test1";
      }
      @GetMapping("/learn/7jack/test2")
      public String dirtest66278(){
          return "learn/7jack/test2";
      }
  
      @PostMapping("/learn/7jack/test1")
      public String dirTest116277(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
          int ians = Integer.parseInt(ans);
          if (ians != 2) {
              double worth = user.getCompanyWorth();
              worth = worth * 0.9;
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/inc1";
          } else {
              Object c =  httpSession.getAttribute("colCount");
              String cs = c.toString();
              int count = Integer.parseInt(cs);
              count += 1;
              Integer countInteger = Integer.valueOf(count);
              String countString = countInteger.toString();
              httpSession.setAttribute("colCount", countString);
              double worth = user.getCompanyWorth();
              Random Random = new Random();
              double ratio = Random.nextDouble();
              worth = worth * (1.0 +(0.2 * ratio));
              System.out.println(worth);
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/col1";
          }
      }
      @PostMapping("/learn/7jack/test2")
      public String dirTest222745(@RequestParam("ans") String ans,@AuthenticationPrincipal User user) {
          int ians = Integer.parseInt(ans);
          if (ians != 4) {
              double worth = user.getCompanyWorth();
              worth = worth * 0.9;
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/inc2";
          } else {
              Object c =  httpSession.getAttribute("colCount");
              String cs = c.toString();
              int count = Integer.parseInt(cs);
              count += 1;
              Integer countInteger = Integer.valueOf(count);
              String countString = countInteger.toString();
              httpSession.setAttribute("colCount", countString);
              double worth = user.getCompanyWorth();
              Random Random = new Random();
              double ratio = Random.nextDouble();
              worth = worth * (1.0 +(0.2 * ratio));
              System.out.println(worth);
              user.setCompanyWorth((int) worth);
              userServiceImpl.updateWorth(user);
              return "learn/7jack/col2";
          }
      }
      @GetMapping("/learn/7jack/fin")
      public String dirfin4275(Model model,@AuthenticationPrincipal User user){
          Object c =  httpSession.getAttribute("colCount");
          String cs = c.toString();
          int count = Integer.parseInt(cs);
          UserBadge userBadge = userBadgeServiceImpl.getUserBadge(user.getId());
          
          System.out.println(count);
          if(!userBadge.isBadge14()){
              BadgeTimeline badgeTimeline = new BadgeTimeline();
              userBadge.setBadge14(true);
              badgeTimeline.setBadgeId(14);
              badgeTimeline.setUserId(user.getId());
              LocalDateTime now = LocalDateTime.now();
              now.plusHours(9);
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
              badgeTimeline.setLtd(now.format(formatter));
              badgeTimelineRepository.save(badgeTimeline);
              badgeTimelineRepository.flush();
              
          }
          
          if(count == 4 && !userBadge.isBadge15()){
              BadgeTimeline badgeTimeline = new BadgeTimeline();
              userBadge.setBadge15(true);
              badgeTimeline.setBadgeId(15);
              badgeTimeline.setUserId(user.getId());
              LocalDateTime now = LocalDateTime.now();
              now.plusHours(9);
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
              badgeTimeline.setLtd(now.format(formatter));
              badgeTimelineRepository.save(badgeTimeline);
              badgeTimelineRepository.flush();
          }
          
          UserStatus userStatus = new UserStatus();
          userStatus.setUserId(user.getId());
          userStatus.setLearnId(7);
          LocalDateTime now = LocalDateTime.now();
          now.plusHours(9);
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
          userStatus.setLtd(now.format(formatter));
          userStatus.setCorrect(count);
          userStatusRepository.save(userStatus);
  
          userBadgeServiceImpl.updateBadge(userBadge);
          model.addAttribute("preWorth", httpSession.getAttribute("preWorth"));
          model.addAttribute("worth", user.getCompanyWorth());
          return "learn/7jack/fin";
      }

      @GetMapping("/learn/lectures/1")
      public String lec1(){
          return "learn/lectures/1";
      }
      @GetMapping("/learn/lectures/2")
      public String lec2(){
          return "learn/lectures/2";
      }
      @GetMapping("/learn/lectures/3")
      public String lec3(){
          return "learn/lectures/3";
      }
      @GetMapping("/learn/lectures/4")
      public String lec4(){
          return "learn/lectures/4";
      }
      @GetMapping("/learn/lectures/5")
      public String lec5(){
          return "learn/lectures/5";
      }
      @GetMapping("/learn/lectures/6")
      public String lec6(){
          return "learn/lectures/6";
      }
      @GetMapping("/learn/lectures/7")
      public String lec7(){
          return "learn/lectures/7";
      }

     



    
}
