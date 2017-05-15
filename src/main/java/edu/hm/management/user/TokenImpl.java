package edu.hm.management.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import edu.hm.management.bib.MediaServiceResult;

/**
 * Implementation of Token Class.
 * @author Daniel Gabl
 *
 */
public class TokenImpl implements IToken  {
    
    /**
     * List of Tokens and their Users.
     */
    private static HashMap<String, User> tokens;
    
    /**
     * Magic Constant.
     */
    private static final long UNIXDIVISOR = 1000L;

    @Override
    public MediaServiceResult generateToken(User usr)  {
        // TODO Check if User is in List
        long unixTime = System.currentTimeMillis() / UNIXDIVISOR;
        String md5string = usr.getName() + usr.getPassword() + unixTime;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            try {
                md.update(md5string.getBytes("UTF-8"));
                String md5 = md.digest().toString();
                
                tokens.put(md5, usr);
                return MediaServiceResult.OKAY;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return MediaServiceResult.BADREQUEST;

    }

    @Override
    public MediaServiceResult validateToken(String token) {
        for (String key : tokens.keySet())  {
            if (key.equals(token))  {
                return MediaServiceResult.OKAY;
            }
        }
        return MediaServiceResult.NOTFOUND;
    }

    @Override
    public User[] getUsers() {
        User[] user = new User[tokens.size()];
        user = tokens.values().toArray(user);
        return user;
    }

    @Override
    public MediaServiceResult addUser(User usr) {
        MediaServiceResult result = MediaServiceResult.DUPLICATEOBJ;
        boolean exists = false;
        for (User user : tokens.values())  {
            if (user.equals(usr))  {
                exists = true;
            }
        }
        if (!exists)  {
            result = generateToken(usr);
        }
        return result;
    }

    @Override
    public MediaServiceResult updateUser(User usr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findUser(String name) {
        for (User user : tokens.values())  {
            if (name.equals(user.getName()))  {
                return user;
            }
        }
        return null;
    }

}
