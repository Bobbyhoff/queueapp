package net.heithoff

import groovy.transform.ToString

@ToString
class Queue {

    static constraints = {
    }

    String firstName
    String lastName
    String contact

    Date dateCreated
    Date lastUpdated


}
