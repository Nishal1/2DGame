<h1 align="center">Comp2000 Java Style Guide <a href="#adapted-from"><sup>1</sup></a></h1>

## 1. Introduction
This document contains the _2021-1_ revision of the Comp2000 Java style guide.
A Java source file is described as _conforming to the Comp2000 Style_ if and only if it adheres to the rules herein.
<br/>
<br/>

### 1.1 Termoinology notes
In this document, unless otherwise clarified:

1. The term _class_ is used to inclusively mean an "ordinary" class, enum class, interface, or annotation type ( `@interface` ).
2. The term _member_ (of a class) is used inclusively to mean a nested class, field, method, or constructor; that is, all top-level contents of a class except initialisers and comments.

Other "terminology notes" will appear occasionally throughout the document.
<br/>
<br/>

### 1.2 Guide notes
Example code in this document is **non-normative**. That is, while the examples are in the Comp2000 Style, they may not illustrate the *only* stylish way to represent the code. Optional formatting choices made in examples should not be enforced as rules.
<br/>
<br/>

## 2.Source file basics

### 2.1 File name
The source file name consists of the case-sensitive name of the top-level class it contains (of which there is [exactly one](#one-top-level-class)), plus the `.java` extension.
<br/>
<br/>

### 2.2 File encoding: UTF-8
Source files are encoded in **UTF-8**.
<br/>
<br/>

### 2.3 Special Characters

#### 2.3.1 Whitespace characters
Aside from the line terminator sequence, the **ASCII horizontal space character (0x20)** is the only whitespace character that appears anywhere in a source file. This implies that:

1. All other whotespace characters in string and character literals are escaped.
2. Tab characters are **not** used for indentation.
<br/>
<br/>

#### 2.3.2 Special escape sequences
For any character that has a [special escape sequence](http://docs.oracle.com/javase/tutorial/java/data/characters.html) `( \\b, \\t, \\n, \\r, \\", \\', and \\\\ )`, that sequence is used rather than the corresponding octal (e.g. <code class="badcode" style="color: #fe1afe;">\012</code>) or Unicode (e.g. <code class="badcode" style="color: #fe1afe;">\u000a</code> ) escape.
<br/>
<br/>

#### 2.3.3 Non-ASCII characters
For the remaining non-ASCII characters, either the actual Unicode character (e.g. `↩`) or the equivalent Unicode escape (e.g. `\u21a9`) is used. The choice depends only on which makes the code **easier to read and understand**. Unicode escapes outside string literals and comments are _not permitted_.

<p style="background-color:#333333; padding:6px 8px 6px 10px; border-left:6px solid #DDC282"><b>Tip</b>: In the Univode escape case, and occasionally even when actual Unicode characters are used, and explanatory comment may be helpful.</p>

Example:

| Example | Discussion |
|---|---|
| String unitAbbrev = "μs"; | Best: perfectly clear even without a comment. |
| String unitAbbrev = "\u03bcs"; // "μs" | Permitted: but there's no reason to do this. |
| <code class="badcode" style="color: #fe1afe;">String unitAbbrev = "\u03bcs"; // Greek letter mu</code> | Not permitted: awkward and prone to mistakes. |
| <code class="badcode" style="color: #fe1afe;">String unitAbbrev = "\u03bcs";</code> | Not permitted: the reader has no idea what this is. |
| return '\ufeff' + content; // byte order mark | Good: use escapes for non-printable characters, and comment if necessary. |
<br/>

## <a id="source-file-structure"></a> 3 Source file structure
A source file consists of, **in order**:

1. Comment block with author's name and student number
2. Package statement, if any
3. Import statements, if any
4. Exactly one top-level class

**Exactly one blank line** separates each section that is present.
<br/>
<br/>

### 3.1 Comment block with author's name and student number
The author's name must be as it appears in iLearn.
<br/>
<br/>

### <a id="package-statement"></a> 3.2 Package statement
The package statement is **not line-wrapped**. The [Column limit](#column-limit) does not apply to package statements.
<br/>
<br/>

### <a id="import-statements"></a> 3.3 Import statements

#### 3.3.1 No wildcard imports
**Wildcard imports**, static or otherwise, **are not used**.
<br/>
<br/>

#### 3.3.2 No line-wrapping
Import statements are **not line-wrapped**. The [Column limit](#column-limit) does not apply to import statements.
<br/>
<br/>

#### 3.3.3 Ordering and spacing
All imports in a single block.
<br/>
<br/>

### 3.4 Class declaration

#### <a id="one-top-level-class"></a> 3.4.1 Exactly one top-level class declaration
Each top-level class resides in a source file of its own.
<br/>
<br/>

#### 3.4.2 Ordering of class contents
The order you choose for the members and initializers of your class can have a great effect on learnability. However, there's no single correct recipe for how to do it; different classes may order their contents in different ways.

What is important is that each class uses **_some_ logical order**, which its maintainer could explain if asked. For example, new methods are not just habitually added to the end of the class, as that would yield "chronological by date added" ordering, which is not a logical ordering.
<br/>
<br/>

#### 3.4.2.1 Overloads: never split
When a class has multiple constructors, or multiple methods with the same name, these appear sequentially, with no other code in between (not even private members).
<br/>
<br/>

## 4 Formatting
**Terminology Note**: block-like construct refers to the body of a class, method or constructor. Note that any [array initializer](#array-initializers) may optionally be treated as if it were a block-like construct.
<br/>
<br/>

## 4.1 Braces

### 4.1.1 Braces are used where optional
Braces are used with `if`, `else`, `for`, `do`, and `while` statements, even when the body is empty or contains only a single statement.
<br/>
<br/>

### <a id="nonempty-blocks"></a> 4.1.2 Nonempty blocks: K & R style
Braces follow the Kernighan and Ritchie style ("[Egyptian brackets](https://blog.codinghorror.com/new-programming-jargon/)") for nonempty blocks and block-like constructs:

1. No line break before the opening brace.
2. Line break after the opening brace.
3. Line break before the closing brace.
4. Line break after the closing brace, only if that brace terminates a statement or terminates the body of a method, constructor, or named class. For example, there is no line break after the brace if it is followed by `else` or a comma.

Examples:

```
return () -> {
  while (condition()) {
    method();
  }
};

return new MyClass() {
  @Override public void method() {
    if (condition()) {
      try {
        something();
      } catch (ProblemException e) {
        recover();
      }
    } else if (otherCondition()) {
      somethingElse();
    } else {
      lastThing();
    }
  }
};
```

There are a few exceptions for [enum classes](#enum-classes).
<br/>
<br/>

### 4.1.3 Empty blocks: may be concise
An empty block or block-like construct may be in K & R style (as described in [Nonempty blocks: K & R style](#nonempty-blocks)). Alternatively, it may be closed immediately after it is opened, with no characters or line break in between (`{}`), unless it is part of a multi-block statement (one that directly contains multiple blocks: `if/else` or `try/catch/finally`).

Examples:

```
  // This is acceptable
  void doNothing() {}

  // This is equally acceptable
  void doNothingElse() {
  }
```
<pre class="prettyprint lang-java badcode" style="color:#fe1afe;">
  // This is not acceptable: No concise empty blocks in a multi-block statement
  try {
    doSomething();
  } catch (Exception e) {}
</pre>
<br/>
<br/>

## 4.2 Block indentation: +2 spaces
Each time a new block or block-like construct is opened, the indent increases by two spaces. When the block ends, the indent returns to the previous indent level. The indent level applies to both code and comments throughout the block. (See the example in [Nonempty blocks: K & R Style](#nonempty-blocks).)
<br/>
<br/>

## 4.3 One statement per line
Each statement is followed by a line break.
<br/>
<br/>

## <a id="column-limit"></a> 4.4 Column limit: 100
Java code has a column limit of 100 characters. A "character" means any Unicode code point. Except as noted below, any line that would exceed this limit must be line-wrapped, as explained in [Line-wrapping](#line-wrapping).

<p style="background-color:#333333; padding:6px 8px 6px 10px; border-left:6px solid #DDC282">Each Unicode code point counts as one character, even if its display width is greater or less. For example, if using fullwidth characters, you may choose to wrap the line earlier than where this rule strictly requires.</p>

Exceptions:
1. Lines where obeying the column limit is not possible (for example, a long URL in Javadoc, or a long JSNI method reference).
2. `package` and `import` statements (see [Package statement](#package-statement) and [Import statements](#import-statements)).
Command lines in a comment that may be cut-and-pasted into a shell.
<br/>
<br/>

## <a id="line-wrapping"></a> 4.5 Line-wrapping
Terminology Note: When code that might otherwise legally occupy a single line is divided into multiple lines, this activity is called line-wrapping.

There is no comprehensive, deterministic formula showing exactly how to line-wrap in every situation. Very often there are several valid ways to line-wrap the same piece of code.

<p style="background-color:#003355; padding:6px 8px 6px 10px; border-left:6px solid #329FFF">Note: While the typical reason for line-wrapping is to avoid overflowing the column limit, even code that would in fact fit within the column limit may be line-wrapped at the author's discretion.</p>

<p style="background-color:#333333; padding:6px 8px 6px 10px; border-left:6px solid #DDC282">Tip: Extracting a method or local variable may solve the problem without the need to line-wrap.</p>
<br/>
<br/>

### 4.5.1 Where to break
The prime directive of line-wrapping is: prefer to break at a **higher syntactic level**. Also:

1. When a line is broken at a non-assignment operator the break comes before the symbol.
  * This also applies to the following "operator-like" symbols:
    * the dot separator (`.`)
    * the two colons of a method reference (`::`)
    * an ampersand in a type bound (`<T extends Foo & Bar>`)
    * a pipe in a catch block (`catch (FooException | BarException e`)).
2. When a line is broken at an assignment operator the break typically comes after the symbol, but either way is acceptable.
  * This also applies to the "assignment-operator-like" colon in an enhanced `for` ("foreach") statement.
3. A method or constructor name stays attached to the open parenthesis (`(`) that follows it.
4. A comma (`,`) stays attached to the token that precedes it.
5. A line is never broken adjacent to the arrow in a lambda, except that a break may come immediately after the arrow if the body of the lambda consists of a single unbraced expression. Examples:
```
MyLambda<String, Long, Object> lambda =
    (String label, Long value, Object obj) -> {
        ...
    };

Predicate<String> predicate = str ->
    longExpressionInvolving(str);
```
<p style="background-color:#003355; padding:6px 8px 6px 10px; border-left:6px solid #329FFF">Note: The primary goal for line wrapping is to have clear code, not necessarily code that fits in the smallest number of lines.</p>
<br/>
<br/>

### 4.5.2 Indent continuation lines at least +4 spaces
When line-wrapping, each line after the first (each continuation line) is indented at least +4 from the original line.

When there are multiple continuation lines, indentation may be varied beyond +4 as desired. In general, two continuation lines use the same indentation level if and only if they begin with syntactically parallel elements.

The section on [Horizontal alignment](#horizontal-alignment) addresses the discouraged practice of using a variable number of spaces to align certain tokens with previous lines.
<br/>
<br/>

## 4.6 Whitespace

### 4.6.1 Vertical Whitespace
A single blank line always appears:

1. _Between_ consecutive members or initializers of a class: fields, constructors, methods, nested classes, static initializers, and instance initializers.
  * **Exception**: A blank line between two consecutive fields (having no other code between them) is optional. Such blank lines are used as needed to create logical groupings of fields.
  * **Exception**: Blank lines between enum constants are covered in [Enum classes](#enum-classes).
2. As required by other sections of this document (such as [Source file structure](#source-file-structure), and [Import statements](#import-statements)).
A single blank line may also appear anywhere it improves readability, for example between statements to organize the code into logical subsections. A blank line before the first member or initializer, or after the last member or initializer of the class, is neither encouraged nor discouraged.

_Multiple_ consecutive blank lines are permitted, but never required (or encouraged).
<br/>
<br/>

### 4.6.2 Horizontal whitespace
Beyond where required by the language or other style rules, and apart from literals, comments and Javadoc, a single ASCII space also appears in the following places **only**.

1. Separating any reserved word, such as `if`, `for`, or `catch`, from an open parenthesis (`(`) that follows it on that line
2. Separating any reserved word, such as `else` or `catch`, from a closing curly brace (`}`) that precedes it on that line
3. Before any open curly brace (`{`), with two exceptions:
  * `@SomeAnnotation({a, b})` (no space is used)
  * `String[][] x = {{"foo"}};` (no space is required between `{{`, by item 8 below)
4. On both sides of any binary or ternary operator. This also applies to the following "operator-like" symbols:
  * the ampersand in a conjunctive type bound: `<T extends Foo & Bar>`
  * the pipe for a catch block that handles multiple exceptions: `catch (FooException | BarException e)`
  * the colon (`:`) in an enhanced `for` ("foreach") statement
  * the arrow in a lambda expression: `(String str) -> str.length()`
but not
  * the two colons (`::`) of a method reference, which is written like `Object::toString`
  * the dot separator (`.`), which is written like `object.toString()`
5. After `,:;` or the closing parenthesis (`)`) of a cast
6. On both sides of the double slash (`//`) that begins an end-of-line comment. Here, multiple spaces are allowed, but not required.
7. Between the type and variable of a declaration: `List<String> list`
8. Optional just inside both braces of an array initializer
  * `new int[] {5, 6}` and `new int[] { 5, 6 }` are both valid
9. Between a type annotation and `[]` or `...`.
This rule is never interpreted as requiring or forbidding additional space at the start or end of a line; it addresses only _interior_ space.
<br/>
<br/>

### 4.6.3 Horizontal alignment: never required
**Terminology Note**: _Horizontal alignment_ is the practice of adding a variable number of additional spaces in your code with the goal of making certain tokens appear directly below certain other tokens on previous lines.

This practice is permitted, but is **never required** by the Comp2000 Java Style. It is not even required to _maintain_ horizontal alignment in places where it was already used.

Here is an example without alignment, then using alignment:

```
private int x; // this is fine
private Color color; // this too

private int   x;      // permitted, but future edits
private Color color;  // may leave it unaligned
```
<p style="background-color:#333333; padding:6px 8px 6px 10px; border-left:6px solid #DDC282">Tip: Alignment can aid readability, but it creates problems for future maintenance. Consider a future change that needs to touch just one line. This change may leave the formerly-pleasing formatting mangled, and that is allowed. More often it prompts the coder (perhaps you) to adjust whitespace on nearby lines as well, possibly triggering a cascading series of reformattings. That one-line change now has a "blast radius." This can at worst result in pointless busywork, but at best it still corrupts version history information, slows down reviewers and exacerbates merge conflicts.</p>
<br/>
<br/>

## 4.7 Grouping parentheses: recommended
Optional grouping parentheses are omitted only when author and reviewer agree that there is no reasonable chance the code will be misinterpreted without them, nor would they have made the code easier to read. It is not reasonable to assume that every reader has the entire Java operator precedence table memorized.
<br/>
<br/>

## 4.8 Specific constructs

### <a id="enum-classes"></a> 4.8.1 Enum classes
After each comma that follows an enum constant, a line break is optional. Additional blank lines (usually just one) are also allowed. This is one possibility:

```
private enum Answer {
  YES {
    @Override public String toString() {
      return "yes";
    }
  },

  NO,
  MAYBE
}
```
An enum class with no methods and no documentation on its constants may optionally be formatted as if it were an [array initializer](#array-initializers)).

```
private enum Suit { CLUBS, HEARTS, SPADES, DIAMONDS }
```
Since enum classes are classes, all other rules for formatting classes apply.
<br/>
<br/>

### 4.8.2 Variable declarations

#### 4.8.2.1 One variable per declaration
Every variable declaration (field or local) declares only one variable: declarations such as <code class="badcode" style="color: #fe1afe;">int a, b;</code> are not used.

**Exception:** Multiple variable declarations are acceptable in the header of a `for` loop.
<br/>
<br/>

#### 4.8.2.2 Declared when needed
Local variables are **not** habitually declared at the start of their containing block or block-like construct. Instead, local variables are declared close to the point they are first used (within reason), to minimize their scope. Local variable declarations typically have initializers, or are initialized immediately after declaration.
<br/>
<br/>

### 4.8.3 Arrays

#### <a id="array-initializers"></a> 4.8.3.1 Array initializers: can be "block-like"
Any array initializer may optionally be formatted as if it were a "block-like construct." For example, the following are all valid (**not** an exhaustive list):

```
new int[] {           new int[] {
  0, 1, 2, 3            0,
}                       1,
                        2,
new int[] {             3,
  0, 1,               }
  2, 3
}                     new int[]
                          {0, 1, 2, 3}
```
<br/>
<br/>

#### 4.8.3.2 No C-style array declarations
The square brackets form a part of the _type_, not the variable: `String[] args`, not <code class="badcode" style="color: #fe1afe;">String args[]</code>.
<br/>
<br/>

### 4.8.4 Switch statements
**Terminology Note:** Inside the braces of a switch block are one or more statement groups. Each statement group consists of one or more _switch labels_ (either `case FOO:` or `default:`), followed by one or more statements (or, for the _last_ statement group, _zero_ or more statements).
<br/>
<br/>

#### 4.8.4.1 Indentation
As with any other block, the contents of a switch block are indented +2.

After a switch label, there is a line break, and the indentation level is increased +2, exactly as if a block were being opened. The following switch label returns to the previous indentation level, as if a block had been closed.
<br/>
<br/>

#### 4.8.4.2 Fall-through: commented
Within a switch block, each statement group either terminates abruptly (with a `break`, `continue`, `return` or thrown exception), or is marked with a comment to indicate that execution will or _might_ continue into the next statement group. Any comment that communicates the idea of fall-through is sufficient (typically `// fall through`). This special comment is not required in the last statement group of the switch block. Example:

```
switch (input) {
  case 1:
  case 2:
    prepareOneOrTwo();
    // fall through
  case 3:
    handleOneTwoOrThree();
    break;
  default:
    handleLargeNumber(input);
}
```
Notice that no comment is needed after `case 1:`, only at the end of the statement group.
<br/>
<br/>

#### 4.8.4.3 The `default` case is present
Each switch statement includes a `default` statement group, even if it contains no code.

**Exception:** A switch statement for an `enum` type may omit the `default` statement group, if it includes explicit cases covering `all` possible values of that type. This enables IDEs or other static analysis tools to issue a warning if any cases were missed.
<br/>
<br/>

### 4.8.5 Annotations
Annotations applying to a class, method or constructor appear immediately after the documentation block, and each annotation is listed on a line of its own (that is, one annotation per line). These line breaks do not constitute [line-wrapping](#line-wrapping), so the indentation level is not increased. Example:

```
@Override
@Nullable
public String getNameIfPresent() { ... }
```

There are no specific rules for formatting annotations on parameters, local variables, or types.
<br/>
<br/>

### 4.8.6 Comments
This section addresses implementation comments.

Any line break may be preceded by arbitrary whitespace followed by an implementation comment. Such a comment renders the line non-blank.
<br/>
<br/>

#### 4.8.6.1 Block comment style
Block comments are indented at the same level as the surrounding code. They may be in `/* ... */` style or `// ...` style. For multi-line `/* ... */` comments, subsequent lines must start with `*` aligned with the `*` on the previous line.

```
/*
 * This is          // And so           /* Or you can
 * okay.            // is this.          * even do this. */
 */
```
Comments are not enclosed in boxes drawn with asterisks or other characters.

<p style="background-color:#333333; padding:6px 8px 6px 10px; border-left:6px solid #DDC282">**Tip:** When writing multi-line comments, use the `/* ... */` style if you want automatic code formatters to re-wrap the lines when necessary (paragraph-style). Most formatters don't re-wrap lines in `// ...` style comment blocks.</p>
<br/>


### 4.8.7 Modifiers
Class and member modifiers, when present, appear in the order recommended by the Java Language Specification:

```
public protected private abstract default static final transient volatile synchronized native strictfp
```
<br/>

### 4.8.8 Numeric Literals
`long`-valued integer literals use an uppercase `L` suffix, never lowercase (to avoid confusion with the digit `1`). For example, `3000000000L` rather than <code class="badcode" style="color: #fe1afe;">3000000000l</code>.
<br/>
<br/>

## 5 Naming

### 5.1 Rules common to all identifiers
Identifiers use only ASCII letters and digits, and, in a small number of cases noted below, underscores. Thus each valid identifier name is matched by the regular expression `\w+` .

Special prefixes or suffixes are **not** used. For example, these names are not permitted: <code class="badcode" style="color: #fe1afe;">name_</code>, <code class="badcode" style="color: #fe1afe;">mName</code>, <code class="badcode" style="color: #fe1afe;">s_name</code> and <code class="badcode" style="color: #fe1afe;">kName</code>.

**Exception:** the `this` keyword should be avoided, so method parameters _may_ be prefixed with `in` and written in [LowerCamelCase](#lower-camel-case) (as demostrated in lectures).
<br/>
<br/>

### 5.2 Rules by identifier type

#### 5.2.1 Package names
Package names are all lowercase, with consecutive words simply concatenated together (no underscores). For example, `com.example.deepspace`, not <code class="badcode" style="color: #fe1afe;">com.example.deepSpace</code> or <code class="badcode" style="color: #fe1afe;">com.example.deep_space</code>.
<br/>
<br/>

#### 5.2.2 Class names
Class names are written in [UpperCamelCase](#upper-camel-case).

Class names are typically nouns or noun phrases. For example, `Character` or `ImmutableList`. Interface names may also be nouns or noun phrases (for example, `List`), but may sometimes be adjectives or adjective phrases instead (for example, `Readable`).

There are no specific rules or even well-established conventions for naming annotation types.

_Test_ classes are named starting with the name of the class they are testing, and ending with `Test`. For example, `HashTest` or `HashIntegrationTest`.
<br/>
<br/>

#### 5.2.3 Method names
Method names are written in [lowerCamelCase](#lower-camel-case).

Method names are typically verbs or verb phrases. For example, `sendMessage` or `stop`.

Underscores may appear in JUnit _test_ method names to separate logical components of the name, with _each_ component written in [lowerCamelCase](#lower-camel-case). One typical pattern is `<methodUnderTest>_<state>`, for example `pop_emptyStack`. There is no One Correct Way to name test methods.
<br/>
<br/>

#### 5.2.4 Constant names
Constant names use `CONSTANT_CASE`: all uppercase letters, with each word separated from the next by a single underscore. But what _is_ a constant, exactly?

Constants are static final fields whose contents are deeply immutable and whose methods have no detectable side effects. This includes primitives, Strings, immutable types, and immutable collections of immutable types. If any of the instance's observable state can change, it is not a constant. Merely _intending_ to never mutate the object is not enough. Examples:

```
// Constants
static final int NUMBER = 5;
static final ImmutableList<String> NAMES = ImmutableList.of("Ed", "Ann");
static final ImmutableMap<String, Integer> AGES = ImmutableMap.of("Ed", 35, "Ann", 32);
static final Joiner COMMA_JOINER = Joiner.on(','); // because Joiner is immutable
static final SomeMutableType[] EMPTY_ARRAY = {};
enum SomeEnum { ENUM_CONSTANT }

// Not constants
static String nonFinal = "non-final";
final String nonStatic = "non-static";
static final Set<String> mutableCollection = new HashSet<String>();
static final ImmutableSet<SomeMutableType> mutableElements = ImmutableSet.of(mutable);
static final ImmutableMap<String, SomeMutableType> mutableValues =
    ImmutableMap.of("Ed", mutableInstance, "Ann", mutableInstance2);
static final Logger logger = Logger.getLogger(MyClass.getName());
static final String[] nonEmptyArray = {"these", "can", "change"};
```

These names are typically nouns or noun phrases.
<br/>
<br/>

#### 5.2.5 Non-constant field names
Non-constant field names (static or otherwise) are written in [lowerCamelCase](#lower-camel-case).

These names are typically nouns or noun phrases. For example, `computedValues` or `index`.
<br/>
<br/>

#### 5.2.6 Parameter names
Parameter names are written in [lowerCamelCase](#lower-camel-case).

One-character parameter names in public methods should be avoided.
<br/>
<br/>

#### 5.2.7 Local variable names
Local variable names are written in [lowerCamelCase](#lower-camel-case).

Even when final and immutable, local variables are not considered to be constants, and should not be styled as constants.
<br/>
<br/>

#### 5.2.8 Type variable names
Each type variable is named in one of two styles:

A single capital letter, optionally followed by a single numeral (such as `E`, `T`, `X`, `T2`)
A name in the form used for classes (see [Class names](#class-names)), followed by the capital letter `T` (examples: `RequestT`, `FooBarT`).
<br/>
<br/>

## 5.3 Camel case: defined
Sometimes there is more than one reasonable way to convert an English phrase into camel case, such as when acronyms or unusual constructs like "IPv6" or "iOS" are present. To improve predictability, Google Style specifies the following (nearly) deterministic scheme.

Beginning with the prose form of the name:

1. Convert the phrase to plain ASCII and remove any apostrophes. For example, "Müller's algorithm" might become "Muellers algorithm".
2. Divide this result into words, splitting on spaces and any remaining punctuation (typically hyphens).
  * Recommended: if any word already has a conventional camel-case appearance in common usage, split this into its constituent parts (e.g., "AdWords" becomes "ad words"). Note that a word such as "iOS" is not really in camel case _per se_; it defies _any_ convention, so this recommendation does not apply.
3. Now lowercase _everything_ (including acronyms), then uppercase only the first character of:
  * ... each word, to yield _upper_ camel case, or
  * ... each word except the first, to yield _lower_ camel case
4. Finally, join all the words into a single identifier.

Note that the casing of the original words is almost entirely disregarded. Examples:

| Prose form | Correct | Incorrect |
|---|---|---|
| "XML HTTP request" | XmlHttpRequest | <code class="badcode" style="color: #fe1afe;">XMLHTTPRequest</code> |
| "new customer ID" | newCustomerId | <code class="badcode" style="color: #fe1afe;">newCustomerID</code> |
| "inner stopwatch" | innerStopwatch | <code class="badcode" style="color: #fe1afe;">innerStopWatch</code> |
| "supports IPv6 on iOS?" | supportsIpv6OnIos | <code class="badcode" style="color: #fe1afe;">supportsIPv6OnIOS</code> |
| "YouTube importer" | YouTubeImporter<br>YoutubeImporter* |  |
|

*Acceptable, but not recommended.

<p style="background-color:#003355; padding:6px 8px 6px 10px; border-left:6px solid #329FFF"><b>Note:</b> Some words are ambiguously hyphenated in the English language: for example "nonempty" and "non-empty" are both correct, so the method names `checkNonempty` and `checkNonEmpty` are likewise both correct, however the former is preferred.</p>
<br/>

## 6 Programming Practices

### 6.1 `@Override`: always used
A method is marked with the `@Override` annotation whenever it is legal. This includes a class method overriding a superclass method, a class method implementing an interface method, and an interface method respecifying a superinterface method.

**Exception:** `@Override` may be omitted when the parent method is `@Deprecated`.
<br/>
<br/>

### 6.2 Caught exceptions: not ignored
Except as noted below, it is very rarely correct to do nothing in response to a caught exception. (Typical responses are to log it, or if it is considered "impossible", rethrow it as an `AssertionError`.)

When it truly is appropriate to take no action whatsoever in a catch block, the reason this is justified is explained in a comment.

```
try {
  int i = Integer.parseInt(response);
  return handleNumericResponse(i);
} catch (NumberFormatException ok) {
  // it's not numeric; that's fine, just continue
}
return handleTextResponse(response);
```

**Exception:** In tests, a caught exception may be ignored without comment if its name is or begins with `expected`. The following is a very common idiom for ensuring that the code under test does throw an exception of the expected type, so a comment is unnecessary here.

```
try {
  emptyStack.pop();
  fail();
} catch (NoSuchElementException expected) {
}
```
<br/>

### 6.3 Static members: qualified using class
When a reference to a static class member must be qualified, it is qualified with that class's name, not with a reference or expression of that class's type.

```
Foo aFoo = ...;
Foo.aStaticMethod(); // good
aFoo.aStaticMethod(); // bad
somethingThatYieldsAFoo().aStaticMethod(); // very bad
```
<br>
<hr/>
<b id="adapted-from"><sup>1</sup></b> Adapted from the <a href="https://google.github.io/styleguide/javaguide.html">Google Java Style Guide</a>.