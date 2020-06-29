# PhotoGallery
PhotoGallery

To Run the Code 

Please Signup on https://api.unsplash.com and Replace client_id in string.xml file

To give you an opportunity to showcase your engineering knowledge and skills, this take home
exercise will have you build a simple but non trivial mobile app. During your interview, this app
will act as a centerpiece for discussion about your thought process and approach to software
architecture. You will walk us through your app as if we were co workers you would be
collaborating with. This will replace the majority of the “whiteboard coding” exercises that is
common in engineering interviews.
Basic app requirements
- App will show a list of images from a 3rd party data source, exposed via some API.
Show these images in a list or grid view. The UX & scrolling experience should be
smooth.
- Tapping on an image or row will take the user to a dedicated detail page for that image.
This detail page should hit the API to fetch additional information for the specific image
and display it below the image.
- The app obviously needs to support at least one 3rd party data source, but should be
architected such that a new data source could be added without much additional work.
Suggestions for photo data source APIs:
- Imgur
- Unsplash
- Shutterstock
- Getty Images
- Your favourite photo API not listed here.
Common Questions
Can I use existing libraries or frameworks?
Generally, yes. If it’s built-in to the iOS/Android platforms; absolutely. If it solves a problem that
doesn’t have much to do with the overall architecture (such as downloading an image from the
internet); yes. But remember that the point of the exercise is to demonstrate your knowledge
and coding ability. If your app is just 5 libraries plugged together and has very little code written
by you, it will be less effective for its purpose. Use your judgement.
How much time should I spend on this?
There is no prescribed amount of time to spend, but 3 -5 hours should be typical. Focus on code
quality and architecture over tons of features or too much UI polish.
App Mock up
Ideas for expansion
Have a minimally viable project complete, but looking to put some more time in? Feel free to
come up with your own ideas of things to add, or take these for inspiration:
- Add an additional data source with option to switch between them.
- Separate photo fetching functionality into an SDK.
- Search / filter photos with some user input.
- Unit testing.
- UX polish
