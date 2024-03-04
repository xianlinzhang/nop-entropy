# Nop平台的定位和发展规划

Nop平台是基于可逆计算理论从零开始构建的，支持面向语言编程范式的新一代低代码平台，它在基本的软件构造原理层面突破了传统的面向对象和组件理论的限制，可以实现系统级别的粗粒度的软件复用。
Nop平台于2023年3月开源，至今已经有近一年左右的时间，逐渐的开始有一些同学将它用在自己的项目中，或者基于它的基本理念重构自己的低代码技术产品。在这种情况下，
有些人可能比较关心Nop平台未来的发展规划是什么，它作为开源项目的可持续性怎么样？在本文中，我将针对一些常见的问题做一个解答。

# 一. Nop平台未来是否会发布商业版？

**Nop平台本身没有商业化目标，所以未来也不会发布商业版本，所有代码都会保持完整开源**。在版权协议方面，Nop平台的前端(nop-chaos)采用MIT协议，后端(nop-entropy)采用AGPL协议，但是增加了对中小企业的商业豁免，允许中小企业在类似Apache2.0协议的情况下使用Nop平台
（Apache2.0协议允许修改源码并免费商用，修改的部分可以保持闭源，但是要求保留所有源码文件中的原作者信息）。我的本意是采用对中小企业更加友好的协议，最好是能促进中小企业之间的协作。

Nop平台的实现集中在后台，主要关注点是提供基本的软件构造原理支持，不会增加大量的应用层的功能特性。如果有比较细致的商业特性需求，建议自行通过模块扩展、Delta定制等机制对平台功能进行商业化增强。
Nop平台的前台目前采用了百度AMIS框架作为缺省的页面渲染框架，整体美观程度和易用程度作为商业产品都有所欠缺，如果对前端要求较高，建议只使用Nop平台的后端。具体配置可以参考[Nop入门：极简服务层开发](https://zhuanlan.zhihu.com/p/679712079)等文章,
在B站上也提供了大量视频演示如何只使用Nop平台的部分模块。

> 网友glennxu和悠闲的水贡献了部分前端代码，前端经过重构后会采用动态插件架构，允许使用华为的opentiny等前端框架作为渲染引擎，以后不会强制要求使用百度AMIS。

Nop平台具有远超现有框架技术的可扩展性，一般的二次开发定制需求都可以在不修改平台基础源码的情况下，通过增加独立的Delta模块实现。具体技术方案介绍参见[如何在不修改基础产品源码的情况下实现定制化开发](https://zhuanlan.zhihu.com/p/628770810)。

如果有通用的功能特性需求，可以在gitee上提交issue。对于确实是广泛使用的共性需求，我会在技术层面提供免费支持，但是我个人不会进行付费的商业化开发，所有我实现的特性需求都会作为开源代码发布。

# 二. Nop平台的研发团队情况如何？能否保证持续性改进？

NopPlatform 2.0的前后端代码全部由我一个人编写完成。其中后端项目nop-entropy目前包含15万行左右手写的Java代码（另外有10多万行自动生成的代码和拷贝的开源代码），
预计最终会达到20万行的手写代码量，并稳定在这个量级。

Nop平台中使用的技术方案经历了二十多年的发展，并在大量软件产品、项目中经过验证，早已形成一整套标准化的软件架构模式。Nop平台的具体实现代码经历了Witrix平台、Entropy平台和现在的Nop平台等多个发展阶段，并经过多次彻底的重构。
在此前的实现中，受限制于产品研发的时间和资源限制，有些能够依据可逆计算理论进行自动化推理的部分是依靠手工编码实现，
另外因为可逆计算理论比较抽象，大部分人难以充分理解，或者理解之后也找不到简洁的技术方案去实现，因此实际实现的时候往往没有达到我的预期。
在最新的实现中，我删除了所有其他人所写的代码，从零开始重新实现了一遍。经过近三年多的重写，现在的实现比较严格的遵循了可逆计算理论，在概念一致性方面大大超越了业内主流的开源框架。

Nop平台的模块结构在2024年应该会基本稳定下来，并为AIGC（人工智能集成）提供比较良好的架构基础。后续我会提供持续的架构改进和开源技术支持。同时，**我也支持
第三方基于Nop平台提供商业化封装，真正实现具有商业价值的技术产品**。

因为基于创新的软件构造原理，Nop平台的代码在实现同样的功能时，比开源框架的代码量要下降一个数量级，更加容易被理解和掌握。例如Nop平台通过3000多行代码就实现
了分布式RPC调用，参见[低代码平台中的分布式RPC框架](https://zhuanlan.zhihu.com/p/631686718)。通过3000多行代码实现了完整的中国式报表展开引擎，
参见[采用Excel作为设计器的开源中国式报表引擎：NopReport](https://zhuanlan.zhihu.com/p/620250740)。所以，有能力的技术团队完全可以全面掌握Nop平台的所有技术细节。
总的来说，Nop平台的总体技术复杂度会限制在单个程序员可以彻底掌握所有细节的水平上（毕竟它的开发就是由单个程序员操刀完成的）。

# 三. Nop平台的性能怎么样？能否支持高并发、大规模的复杂应用？

Nop平台在性能方面领先于主流的Spring框架。这种领先源于两个方面：

## 1. 软件构造原理方面的领先优势
Nop平台基于可逆计算理论大量使用编译期转换和即时编译，因此很多传统框架需要在运行期执行的复杂判断逻辑在Nop平台中都被转移到了编译期，大量复杂的模型抽象并不会导致额外的性能损失。

## 2. 实现层面的后发优势
Nop平台是最近几年彻底从零开始构建的新一代框架，因此它可以吸取此前其他框架多年发展后得到的经验教训，集中精力优化少数真正常用的代码执行路径。而传统的框架诞生于多年以前，兼容性的拖累使得它们总是要在运行时执行很多额外的多余动作。
比如说，NopGraphQL引擎可以同时对外提供GraphQL和REST两种形式的接口服务，其中REST方式下统一采用JSON格式进行请求数据和响应数据的编解码，而SpringMVC框架出于兼容性的考虑，不得不在框架内包含多种编码策略，并在运行时执行很多多余的判断。
传统的Web框架不可能同时支持GraphQL和REST协议，如果单独通过graphql-java来暴露GraphQL服务，则必然会引入大量重复的格式转换和接口适配工作。

另外一方面，现有的工作流引擎、IoC引擎、ORM引擎、Web引擎、规则引擎、批处理引擎等都是各个开源团队分别设计并实现，然后由Spring再次进行封装整合到统一的框架中，
这导致这些引擎底层存在大量概念重复，它们在协同工作时也需要经过非常冗长的适配接口。进行扩展的时候我们不得不学习各个框架不同风格的扩展机制，并学会处理这些框架之间潜在的实现冲突。
在Nop平台中，所有的框架采用相同的XDef元模型描述，并且在XDSL层面通过统一的Delta差量化机制来实现自定义扩展，因此它可以减少很多信息转换损耗，并用最小的成本实现多个引擎的无缝集成。

笔者本人所在的公司是银行核心系统的软件提供商，笔者对于高并发、高复杂度的toB软件开发颇有心得，也有很多国产化信创软件产品的开发经验，因此Nop平台对于高性能以及高复杂度的toB软件产品开发已经内置了很多支持，并在实践中经历了大量验证。

# 四. Nop平台对于各类技术标准的支持完整度如何？能否与第三方微服务协同工作？

Nop平台的所有核心功能都没有采用第三方开源组件，全部都是从零开始编写实现。比如说，它的XML解析没有使用第三方XML解析库，而是在nop-core模块中自行实现了一个XNodeParser。再比如它在实现graphql协议时没有使用开源的graphql-java框架，
而是自己编写GraphQLDocumentParser，并实现了一个采用诸多创新设计的GraphQLEngine。有些人可能会有疑问：如果不使用这些第三方库，如何保证自己的实现与外部的标准（如GraphQL协议标准）百分百兼容呢？
对于这个问题，Nop平台的回答是：**无法做到百分百兼容，也没有必要做到**。这里的原因分为三个方面：

## 首先是实现成本方面的考量
因为整个Nop平台的核心代码由我一人完成，所以在技术标准方面我只会采用一种保守的技术策略：**Nop平台只使用标准中与可逆计算理论兼容的某个子集部分，并不追求对标准各个细节的覆盖**。
以NopGraphQL引擎为例，它对外提供标准的GraphQL服务，可以使用第三方的graphiql开发工具进行调试，也可以与SpringCloud微服务框架协同工作。但是，NopGraphQL并不支持GraphQL标准中的所有细节，也没有核对过GraphQL协议支持的完整度有多高。
它对GraphQL标准的实现程度由NopGraphQL引擎实际使用过程中用到的部分来决定，没有发现实际用途的特性在现在的代码中并不会实现。类似的，在NopReport报表引擎的实现中，表达式中支持的Excel函数只包含最常见的几个函数，并不会像商业产品那样提供几百个Excel兼容的表达式函数。

如果确有需求，建议自行编写插件来实现扩展的各类要求。（Nop平台的核心模块中都内置了各个抽象层面的plugin接口，可以按照自己的需要进行扩展）。

总的来说，目前的主流技术架构对于异构系统集成已经有了比较成熟的解决方案，Nop平台对外暴露的接口一致性非常高，很容易适配到各类外部接口标准，从而形成一个完整的服务集群。但是对于原标准中应对各种小众需求的特性，Nop平台并不使用，因此也就并不支持。

## 第二是安全性和最佳实践的考量

一般的技术标准中对同一个功能往往会提供多种可选的实现途径，但是它们的安全性和易用性各不相同。真正在实践中，我们往往只会推荐少数的最佳实践方案，但是出于兼容性考虑，标准内部会保留大量不符合最佳实践的技术途径，这也会导致在无意中引入安全性漏洞。

因为笔者长期从事的是高端、复杂的大型软件系统的研发，对于安全性、合规性等有着较高要求，所以在Nop平台中一般只会保留安全性较高、符合目前最佳实践标准的功能特性。比如说XML标准中包含自定义Entity的内容，但是它很容易引入安全性bug，此前在世界范围内亚马逊、谷歌、腾讯、阿里等大公司都出现过
XML解析器导致的0day bug，原因就在于这些很少有人使用但是又内置在标准中的复杂特性。Nop平台选择是只支持XML标准中最常用的一个子集，放弃所有需要加载外部实体对象的技术特性，从而在根源上杜绝此类bug的发生。

Nop平台的实现中对安全性进行了大量加强，比如GraphQL语句的长度、其中包含的操作个数、允许的嵌套层次等都具有缺省限制（可以通过配置参数调整），确保在缺省情况下满足相当高的安全性标准，可以抵抗常见的注入攻击、资源耗尽攻击等。

## 第三也是最重要的是，创新的设计不应受到已有形式的约束

可逆计算理论是下一代的软件构造理论，Nop平台是可逆计算理论指导下实现的下一代的软件开发平台。所以，Nop平台所关注的是目前的软件框架技术所无法达到的、目前尚属空白的软件设计空间。
它的重点是在无人区中探索新的软件结构构造规律，提出创新的技术解决方案，解决前人从未能够解决的技术难题，因此它的实现不会受到目前的技术标准的限制，甚至可以说它压根就不在意对现有技术标准的兼容性。
如果标准中的特性定义与可逆计算理论兼容，则可以直接采用。如果在可逆计算的实践中用不上甚至有冲突，则所谓的标准并不会被纳入考量。
整个Nop平台的研发过程就是扛着炸药包上路，逢山炸山，遇水填河，大道朝天，一路向前。

传统上的设计大都只是是满足于解决当前的业务问题，但是Nop平台它所需要解决的问题范围却包括跨系统、跨软件生命周期的整体演化问题。
因此**Nop平台对信息表述的完整性和局部可分析性的重视大大超过了对于所谓强大功能的追求**，它也必然会对已有的技术形式中无法满足可逆计算要求的部分进行改造。

# 五. Nop平台的发展定位是什么？

首先必须强调的是，Nop平台的定位**绝对不是一个大众化的流行框架**。我研发Nop平台的主要目的是**作为可逆计算理论的参考实现**，从而澄清可逆计算理论中的一些技术思想。
它的一个副作用是基于整个业内最新的一些技术认知，提供当前和未来一段时间内的最佳实践，促进对新的软件架构模式的思考，提升国内软件架构设计的整体水平。所以，Nop平台不会成为一个
开箱即用、用户友好，在细节上精细打磨的开发框架，它也不会成为一个成熟的、可以立刻创造商业价值的软件产品。

在实用的意义上，Nop平台的定位是**一个基于新的软件构造原理的技术底座**。如果你想开发一个类似Mendix、OutSystems这样具有广泛适应性的通用型低代码产品，想与世界上最优秀的技术产品竞争，
可以考虑在底层使用Nop平台或者借鉴它的原创设计，节省你自己的开发时间。良好的产品设计加上Nop平台领先的原理架构，可以起到事半功倍的作用。

Nop平台规划中的组件以及它们的开发进度在[README.md](https://gitee.com/canonical-entropy/nop-entropy/blob/master/README.md)文件中已经有详细的列举，
预计在2024年至2025年完成这些组件的开发，并正式发布到maven中心仓库。

# 题外的话

黑客之王Linus Torvalds说，talk is cheap, show me the code。Nop平台已经全部开源，源码就在那里，一切疑惑和争议都在源码中可以获得解答。

有趣的是，国内现在很多人不想看code，更不耐烦去理解别人的思想，只想让人show me the money（用这个平台能挣钱吗？挣到钱了吗？）。说实话，这不是符合黑客精神的交流方式。
更有趣的是，最喜欢在朋友圈发文批判国内实用化思想泛滥、不能沉下心来做技术研究、中国的技术落后是源于思想落后，开口闭口国家堵住了他的创新之路的人，恰恰是开口闭口不离money的同一伙人。
对于这种精神分裂现象，我只能理解为没挣到钱之后急得有点面红耳赤，语无伦次了。

很多人觉得可逆计算这个名词听起来过于高大上，像是民科的做派，再听说作者几乎从不翻墙，很少阅读国外的技术文献之后更是感到不可思议。有人就想问，2024年了，作者家还没联网吗？这是闭门造车吗？
这里可以解释一下，作者虽然不翻墙，但是他读各类开源软件的源码啊，一般尽量通过源码而不是别人的文章来了解相关的技术内容。另外作者的学术背景是理论物理学，受过良好的理论科班训练，平时也多阅读数理相关的文献。
可逆计算理论的起因就是作者尝试将物理学的思想引入软件工程领域所做的有意识的努力。作者之所以能够发明一些原创的技术方案，恰在于他的思想没有像计算机科班的同学那样受到传统思想的束缚。
目前一般程序员对于抽象的理论知识的了解程度限制在大学本科水平，基本是停留在牛顿和莱布尼兹的时代，如果不努力提升自己对抽象知识的理解能力，那么即使是将互联网上所有的计算机相关的信息一网打尽，
那也是与人类世界最近200多年最优秀的头脑所发现的知识世界无缘的。

有些同学反映可逆计算理论难以理解，这也是一种正常现象。因为大部分人并不真的需要理论解释，本身都是在别人的规范下工作，只是自己脑补一下这样做的好处而已
（正如大部分人并不需要理解抽象的关系模型就可以熟练使用关系数据库）。而Nop平台的做法是创造自己的规范，所以对一般人来说和说天书也没什么区别。有兴趣的话，只看代码就好了。
