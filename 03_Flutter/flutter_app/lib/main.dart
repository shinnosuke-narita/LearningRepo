import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:math';
import 'dart:ui' as ui;
import 'dart:io';
import 'dart:convert';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter_otp_text_field/flutter_otp_text_field.dart';

const kColorPrimary = Color(0xFF2E8376);
const kColorText = Color(0xFF9E9E9E);
const kColorTextDark = Color(0xFF212121);
const kColorFlightText = Color(0xFFE0E0E0);
const kColorFlightIcon = Color(0xFFC1B695);
const kColorTicketBorder = Color(0xFFE0E0E0);
const kSingaporeLogoUrl = 'https://user-images.githubusercontent.com/7200238/82220821-1ebc8880-995a-11ea-9d77-07edda64f05c.png';
const kQantasLogoUrl = 'https://user-images.githubusercontent.com/7200238/82220824-1fedb580-995a-11ea-8124-f59daff4ebda.png';
const kEmiratesLogoUrl = 'https://user-images.githubusercontent.com/7200238/82220816-1c5a2e80-995a-11ea-921d-38b3f991d8d2.png';
const kHainanLogoUrl = 'https://user-images.githubusercontent.com/7200238/82223309-73adce00-995d-11ea-98c0-2dba4e094aca.png';

void main() async {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  final title = 'Flutter サンプル';


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        primaryColor: const Color(0xFF2E8376),
      ),
      home: const FirstScreen(),
    );
  }
}

class FirstScreen extends StatefulWidget {
  const FirstScreen({Key? key}) : super(key: key);

  @override
  _FirstScreenState createState() => _FirstScreenState();
}

class _FirstScreenState extends State<FirstScreen> with SingleTickerProviderStateMixin{
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
       appBar: AppBar(
         backgroundColor: kColorPrimary,
         elevation: 0,
         leading: const Icon(Icons.navigate_before),
       ),
       body:  Column(
           mainAxisAlignment: MainAxisAlignment.center,
           mainAxisSize: MainAxisSize.max,
           children: [
             OtpTextField(
              numberOfFields: 6,
              borderColor: Color(0xFF512DA8),
              //set to true to show as box or false to show as dash
              showFieldAsBox: true,
             ),
           ],
         ),
    );
  }
}

class _PutCodeBox extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 48,
      height: 52,
      decoration: BoxDecoration(
        color: Colors.grey,
        border: Border.all(color: Color.fromARGB(1, 191, 191, 191), width: 1),
        boxShadow: [
          BoxShadow(
            blurRadius: 20.0,
            color: Colors.white24.withOpacity(0.5),
            offset: Offset(5, 7)
          )],
        ),

      child:  const TextField(
        maxLength: 1,
        decoration: InputDecoration(
          border: InputBorder.none,
          counterText: '',
          )
        ),
    );
  }
}










class _DestinationText extends StatelessWidget {
  final String title;
  final String subTitle;

  const _DestinationText({
    Key? key,
    required this.title,
    required this.subTitle
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(title, style: const TextStyle(color: Colors.white, fontSize: 48),),
        Text(subTitle, style: const TextStyle(color: kColorText))
      ],
    );
  }
}

class _FlightIcon extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 52,
      height: 52,
      decoration: BoxDecoration(
        border: Border.all(
          color: kColorFlightIcon,
          width: 2,
        ),
        borderRadius: BorderRadius.circular(52 / 2)
      ),
      child: Transform.rotate(
        angle: pi / 2,
        child: const Icon(
          Icons.flight,
          color: kColorFlightIcon,
          size: 28,
        ),
      ),
    );
  }
}

class FlightInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context ) {
    return  Column(
      children:   [
        Material(
          color: kColorPrimary,
          elevation: 24,
          shadowColor: kColorPrimary,
          borderRadius:  const BorderRadius.only(
            bottomRight:Radius.circular(24) ,
            bottomLeft: Radius.circular(24),
          ),
          child: Padding(
            padding: const EdgeInsets.only(
              left: 24,
              right: 24,
              bottom: 32
            ),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    const _DestinationText(title: "DHK", subTitle:  'Dhaka'),
                    _FlightIcon(),
                    const _DestinationText(title: "LDN", subTitle: 'London'),
                  ],
                ),
                const SizedBox(height: 32,),
                Container(
                  alignment: Alignment.centerLeft,
                  child: Text(
                    'Monday, 18 May, 2020',
                    style: TextStyle(color: kColorFlightText.withOpacity(0.5)),
                  ),
                )
              ],
            ),
          ),
        )
      ],
    );
  }
}












