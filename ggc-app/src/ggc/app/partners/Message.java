package ggc.app.partners;

/** Messages for partner menu interactions. */
interface Message {

  static String showAllPartners(String partners){
    return partners;
  }

  static String showPartner(String partner){
    return partner;
  }
}