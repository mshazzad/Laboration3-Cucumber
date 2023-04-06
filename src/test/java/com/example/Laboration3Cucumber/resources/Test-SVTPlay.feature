Feature: Test SVTPlay

  Scenario: SVTPlay should show correct title
    Given Chrome web driver in available
    When  User visits SVTPlay
    Then  Title should be "SVT Play"

  Scenario: SVTPlay logo should visible
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    Then  logo should visible
    And   Text should be "SVT Play logotyp"

  Scenario: Main many's links should be Start, Program, Kanaler
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User Navigate to Links
    Then  Main many should be "Start", "Program", "Kanaler"

  Scenario: Availability within SVTPlay is visible and correct link text shows
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    Then  Availability is visible with right linktext "Tillgänglighet i SVT Play"

  Scenario: Correct contents should shows by following Availability
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User Navigate to Availability
    Then  Right contents heading should be "Så arbetar SVT med tillgänglighet"

  Scenario: Program categories should count
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User navigate to Program
    Then  It should count amount of categories  17

  Scenario: Search option should work properly
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User search Filmer by using search function
    Then  Right search categories should shows "Filmer"


  Scenario: Contents from other pages should shows
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User Navigate to Channel to find out TV tables
    Then  Content findings should be "Tv-tablå"

  Scenario: User can back to Start page from other pages
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User navigate to news page
    Then  User aims to back start page
    And   Start page title should be "SVT Play"




  Scenario: User can see child contents
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User navigate to child category
    Then  Child category should displayed & text should be "BARN"

  Scenario: SVTPlay should redirect to SVT's home page
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User click link svt.se
    Then  User Should get svt website & title should be "SVT Nyheter"


  Scenario: Program categories should match
    Given Chrome web driver in available
    When  User visits SVTPlay
    And   User accept cookies
    And   User navigate to Program
    Then It should shows categories "Serier","Nyheter", "Dokumentär","Sport", "Barn", "Filmer", "Underhållning", "Livsstil & reality", "Fakta", "Humor", "Drama","Kultur", "Musik", "Samhälle", "Djur & natur", "Scen", "Öppet arkiv"
