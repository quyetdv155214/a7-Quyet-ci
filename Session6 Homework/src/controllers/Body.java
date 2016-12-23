package controllers;

import models.Model;

/**
 * Created by q on 12/14/2016.
 */
public interface Body {
     Model getModel();
     void onContact(Body orther);
}
