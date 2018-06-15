# TourItinerary Android App

HPCL Project
![hindustan_petroleum_logo svg](https://user-images.githubusercontent.com/18751913/41452002-5ad53990-708d-11e8-94e2-a28bb508995a.png)

### WorkDone

1. 2 component - CreateTI and ListTI
2. ListTI - it is a recycler View of already created TI
3. CreateTI - takes officer details for creating a Tour Itinerary
4. AddTI - displays a summary of Officer details and captures intent from AddTI_Form to create a list of TI created recently
5. AddTI_Form - form that takes Tour details and generated a TI by sending details via intent
6. A sample JSON dataset created to fetch officer and TI details

### TODO

### CreateTI
1. Spinner Text "Select one option" add and update spinner
2. Update UI

### AddTI
1. Retrieve All Data from AddTI_form and set some in RecyclerView and add all in Database.
2. Final Submission button UI change
3. Final submission takes to ListTI which queries from HPCL database.

### AddTI_form
1. Read City names from JSON data file
2. Contraint TO and FROM should not have same city
3. UI improvements needed.

### Additional
1. Need to create API for HPCL
2. Integrate API with current App
3. Redesign UI



