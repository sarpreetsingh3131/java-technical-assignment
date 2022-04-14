# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

------
I follow strategy pattern to implement discounts. In future, if we want to implement new discount we just implement the Discount interface and then include that class in the DiscountRule.


Due to shortage of time, I did not implement error handling and their tests, for example, 
- look for price which is <= £0
- implement separate tests classes for the classes that I implemented, etc.
